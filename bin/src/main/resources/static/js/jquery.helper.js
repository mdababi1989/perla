$(document).ready(function(){
	
	var effectSpeed = 150;
	var timeout = 500;
	var $layer = 0;
	
	var typewatch = (function(){
	  var timer = 0;
	  return function(callback, ms){
	    clearTimeout (timer);
	    timer = setTimeout(callback, ms);
	  }  
	})();
	
	
	/**
	 * Display hint in obsolete browsers
	 * 
	 * If someone's visiting the website with an old browsr, they'll see a bar on top
	 */
	
	//browserUpdateScript = $('<script>').attr({'type': 'text/javascript', 'src': 'http://browser-update.org/update.js'});
	//$('body').append(browserUpdateScript);
	
	
	/**
	 * Mobile detection
	 * 
	 * Searched for a mobile devices
	 * @todo Expand script to detect more mobile devices
	 */
	
	var deviceAgent = navigator.userAgent.toLowerCase();
	var is_mobileDevice = deviceAgent.match(/(iphone|ipod|ipad|android)/);
	
	
	/**
	 * Function for making menus fancy
	 * 
	 * @param  $el wrapper  object  element; ensuring the uniqueness
	 * @param  $trigger  string  element which triggers events
	 * @param  $target  string  element to affect by the trigger
	 * @param  $dblClk  bool  rule for handling a-tags at trigger; useful to avoid jumping to subpages at e.g. mobile devices
	 * @param  $recursive  bool  Apply trigger recursive?
	 * @param  $delay  bool  Apply a delay an the deeper layers?
	 */
	
	var toggleNavEl = function ($el, trigger, target, dblClk, delay) {
		
		if ( ($el.size() > 0 && trigger.length > 0) ) {
			// Set default values
			target = (target) ? target : false;
			dblClk = (dblClk) ? dblClk : true;
			delay = (delay) ? delay : false

			// Pick all Subs
			var $subs = $el.find( trigger );

			// Handle the Subs
			$subs.each( function() {
				var $sub = $(this);
				var $target = $sub.find( ' > ' + target );
				var deeperLayer = $target.parents('.slideEl').length;
				$target.data('height', $target.actual('height') );
				$target.css( {height: 0, display: 'none', opacity: 0} );

				if (dblClk && is_mobileDevice) {
					$sub.children('a')
						.click( function() {
							$tmpEl = $(this);
							if ($tmpEl.data('clicked') == true) {
								return true;
							} else {
								$tmpEl.data('clicked', true);
								return false;
							}
						})
						.blur( function() {
							$tmpEl = $(this);
							$tmpEl.data('clicked', false);
						});
					// $sub.children()
					
				}
				
				$sub
					.mouseenter( function() {
						$target.stop();
						if (delay && deeperLayer && !is_mobileDevice) {
							$target.doTimeout( 'fadeIn', timeout, function() {
								$(this).css({display:'block'}).animate( {height: $target.data('height'), opacity: 1}, effectSpeed );
							});
						} else {
							$target.css({display:'block'}).animate( {height: $target.data('height'), opacity: 1}, 150 );
						}
					})
					.mouseleave( function() {
						$target.stop();
						if (delay && deeperLayer && !is_mobileDevice) {
							$target.doTimeout( 'fadeIn', timeout, function() {
								$(this).animate( {height: 0, opacity: 0}, effectSpeed, function(){
									$target.css({display:'none'});
								})
							});	
						} else {
							$target.animate( {height: 0, opacity: 0}, 150, function(){
								$target.css({display:'none'});
							});				
						}
					})
				// $sub	
	
			});
				
		} else {
			// Do nothing

		}
	}
	
	
	/**
	 * Init stuff
	 * 
	 * Picking elements, stuffing them with fancy effects, etc.
	 */
	
	// Cache the objects
	var navAdd = $('#navAdd');
	var navMain = $('#navMain');
	// Lavalampify the main nav
	if (navMain.size() != 0) {
		navMain.lavaLamp({speed:300,fx:'easeOutExpo'});
	}
	// Let all navs in the header be fancy
	toggleNavEl(navAdd, 'li.sub', '.slideEl', true, false);
	toggleNavEl(navMain, 'li.sub', 'ul.slideEl', true, true);
	
	
	/**
	 * Social nav fader
	 */
	
	var social = $('#social');
	if (social.size() != 0) {
		el = social.children('li');
		el.css({opacity:.5});
		el.mouseenter(function() {
			$(this).stop(true,false).animate({'opacity':1},150);
		}).mouseleave(function() {
			$(this).stop(true,false).animate({'opacity':.5,},150);
		});
	}
	
	
	/**
	 * Simple JumpTo
	 */
	
	var jumperEl = 'a';
	var jumperAttr = 'id';
	var jumperID = 'jumper';
	
	if ($(jumperEl).filter('['+jumperAttr+'='+jumperID+']').size() != 0) {
		var jumperHandle = $(jumperEl).filter('[' + jumperAttr + '=' + jumperID + ']');
		jumperHandle.each(function() {
			$(this).click(function() {
				var jumpTarget = $(this).attr('href');
				var idPos = jumpTarget.indexOf('#');
				jumpTarget = jumpTarget.substring(idPos);
				$('html,body').animate({scrollTop: $(jumpTarget).offset().top},'slow');
				return false;
			});
		})
	}
	
});


/**
 * Rewritten Lavalamp plugin
 */

jQuery.fn.lavaLamp = function(options) {
	this.each(function() { new LavaLamp(this, options); });
	return this;
};

function LavaLamp(ele, options) {
	this.$root		= $(ele);
	this.options	= $.extend(LavaLamp.DEFAULTS, options || {});
	this._init();
}

jQuery.extend(LavaLamp, {
	DEFAULTS: {
		fx: 	"linear",
		speed: 	500,
		click: 	function() {}
	},
	get: function(ele) {
		return $.data(ele, 'active.lavalamp');
	}
});

LavaLamp.prototype = {
	setCurr: function(ele) {
		if (typeof ele == 'number') {
			if (ele == 0) return;
		}
		ele = this._resolveElement(ele);
		this.$back.css({ "left": ele.offsetLeft + "px", "width": ele.offsetWidth + "px" });
        this.curr = ele;
	},
	_init: function() {
		var self = this;
		this.$li = this.$root.find('> li').hover(function() {
			self._move(this);
		}, function() {}).click(function(evt) {
			self.setCurr(this);
			return self.options.click.apply(this, [evt, this]);
		});
		this.curr = this.$root.find('li.active')[0] || 0;
		this.$root.hover(
			function() {}, 
			function() { self._move(self.curr) }
		);
		this.$back = $('<li class="back"><div class="left"></div></li>').appendTo(this.$root);
		this.setCurr(this.curr);
		this.$root.data('active.lavalamp', this);
	},
	_move: function(ele) {
		ele = this._resolveElement(ele);
		this.$back.each(function() {
			$(this).dequeue("fx"); }
		).animate({
			width: ele.offsetWidth,
			left: ele.offsetLeft
		}, this.options.speed, this.options.fx);
	},
	_resolveElement: function(thing) {
		if (typeof thing == 'number') {
			return this.$li.eq(thing)[0];
		} else if (typeof thing == 'string') {
			return this.$li.filter(thing)[0];
		} else {
			return thing;
		}
	}
};


/**
 * Plugin to get height and width of hidden element with hidden parents
 */

;(function($){
  $.fn.extend({
    actual : function( method, options ){
      var $hidden, $target, configs, css, tmp, actual, fix, restore;

      // check if the jQuery method exist
      if( !this[ method ]){
        throw '$.actual => The jQuery method "' + method + '" you called does not exist';
      }
      
      configs = $.extend({
        absolute : false,
        clone : false
      }, options );

      $target = this;
      
      if( configs.clone === true ){
        fix = function(){
          // this is useful with css3pie
          $target = $target.filter( ':first' ).clone().css({
            position : 'absolute',
            top : -1000
          }).appendTo( 'body' );
        };
        
        restore = function(){
          // remove DOM element after getting the width
          $target.remove();
        };
      } else {
        fix = function(){
          // get all hidden parents
          $hidden = $target.parents().andSelf().filter( ':hidden' );

          css = configs.absolute === true ?
            { position : 'absolute', visibility: 'hidden', display: 'block' } :
            { visibility: 'hidden', display: 'block' };

          tmp = [];

          // save the origin style props
          // set the hidden el css to be got the actual value later
          $hidden.each( function(){
            var _tmp = {}, name;
            for( name in css ){
              // save current style
              _tmp[ name ] = this.style[ name ];
              // set current style to proper css style
              this.style[ name ] = css[ name ];
            }
            tmp.push( _tmp );
          });
        };
        
        restore = function(){
          // restore origin style values
          $hidden.each( function( i ){
            var _tmp = tmp[ i ], name;
            for( name in css ){
              this.style[ name ] = _tmp[ name ];
            }
          });
        };
      }

      fix();
      // get the actual value with user specific methed
      // it can be 'width', 'height', 'outerWidth', 'innerWidth'... etc
      actual = $target[ method ]();

      restore();
      // IMPORTANT, this plugin only return the value of the first element
      return actual;
    }
  });
})(jQuery);


/*!
 * jQuery doTimeout: Like setTimeout, but better! - v1.0 - 3/3/2010
 * http://benalman.com/projects/jquery-dotimeout-plugin/
 * 
 * Copyright (c) 2010 "Cowboy" Ben Alman
 * Dual licensed under the MIT and GPL licenses.
 * http://benalman.com/about/license/
 */

// Script: jQuery doTimeout: Like setTimeout, but better!
//
// *Version: 1.0, Last updated: 3/3/2010*
// 
// Project Home - http://benalman.com/projects/jquery-dotimeout-plugin/
// GitHub       - http://github.com/cowboy/jquery-dotimeout/
// Source       - http://github.com/cowboy/jquery-dotimeout/raw/master/jquery.ba-dotimeout.js
// (Minified)   - http://github.com/cowboy/jquery-dotimeout/raw/master/jquery.ba-dotimeout.min.js (1.0kb)
// 
// About: License
// 
// Copyright (c) 2010 "Cowboy" Ben Alman,
// Dual licensed under the MIT and GPL licenses.
// http://benalman.com/about/license/
// 
// About: Examples
// 
// These working examples, complete with fully commented code, illustrate a few
// ways in which this plugin can be used.
// 
// Debouncing      - http://benalman.com/code/projects/jquery-dotimeout/examples/debouncing/
// Delays, Polling - http://benalman.com/code/projects/jquery-dotimeout/examples/delay-poll/
// Hover Intent    - http://benalman.com/code/projects/jquery-dotimeout/examples/hoverintent/
// 
// About: Support and Testing
// 
// Information about what version or versions of jQuery this plugin has been
// tested with, what browsers it has been tested in, and where the unit tests
// reside (so you can test it yourself).
// 
// jQuery Versions - 1.3.2, 1.4.2
// Browsers Tested - Internet Explorer 6-8, Firefox 2-3.6, Safari 3-4, Chrome 4-5, Opera 9.6-10.1.
// Unit Tests      - http://benalman.com/code/projects/jquery-dotimeout/unit/
// 
// About: Release History
// 
// 1.0 - (3/3/2010) Callback can now be a string, in which case it will call
//       the appropriate $.method or $.fn.method, depending on where .doTimeout
//       was called. Callback must now return `true` (not just a truthy value)
//       to poll.
// 0.4 - (7/15/2009) Made the "id" argument optional, some other minor tweaks
// 0.3 - (6/25/2009) Initial release

(function($){
  '$:nomunge'; // Used by YUI compressor.
  
  var cache = {},
  // Reused internal string.
  doTimeout = 'doTimeout',
  // A convenient shortcut.
  aps = Array.prototype.slice;
   
  $[doTimeout] = function() {
    return p_doTimeout.apply( window, [ 0 ].concat( aps.call( arguments ) ) );
  };
  
  $.fn[doTimeout] = function() {
    var args = aps.call( arguments ),
      result = p_doTimeout.apply( this, [ doTimeout + args[0] ].concat( args ) );
    
    return typeof args[0] === 'number' || typeof args[1] === 'number'
      ? this
      : result;
  };
  
  function p_doTimeout( jquery_data_key ) {
    var that = this,
      elem,
      data = {},
      
      // Allows the plugin to call a string callback method.
      method_base = jquery_data_key ? $.fn : $,
      
      // Any additional arguments will be passed to the callback.
      args = arguments,
      slice_args = 4,
      
      id        = args[1],
      delay     = args[2],
      callback  = args[3];
    
    if ( typeof id !== 'string' ) {
      slice_args--;
      
      id        = jquery_data_key = 0;
      delay     = args[1];
      callback  = args[2];
    }
    
    // If id is passed, store a data reference either as .data on the first
    // element in a jQuery collection, or in the internal cache.
    if ( jquery_data_key ) { // Note: key is 'doTimeout' + id
      
      // Get id-object from the first element's data, otherwise initialize it to {}.
      elem = that.eq(0);
      elem.data( jquery_data_key, data = elem.data( jquery_data_key ) || {} );
      
    } else if ( id ) {
      // Get id-object from the cache, otherwise initialize it to {}.
      data = cache[ id ] || ( cache[ id ] = {} );
    }
    
    // Clear any existing timeout for this id.
    data.id && clearTimeout( data.id );
    delete data.id;
    
    // Clean up when necessary.
    function cleanup() {
      if ( jquery_data_key ) {
        elem.removeData( jquery_data_key );
      } else if ( id ) {
        delete cache[ id ];
      }
    };
    
    // Yes, there actually is a setTimeout call in here!
    function actually_setTimeout() {
      data.id = setTimeout( function(){ data.fn(); }, delay );
    };
    
    if ( callback ) {
      // A callback (and delay) were specified. Store the callback reference for
      // possible later use, and then setTimeout.
      data.fn = function( no_polling_loop ) {
        
        // If the callback value is a string, it is assumed to be the name of a
        // method on $ or $.fn depending on where doTimeout was executed.
        if ( typeof callback === 'string' ) {
          callback = method_base[ callback ];
        }
        
        callback.apply( that, aps.call( args, slice_args ) ) === true && !no_polling_loop
          
          // Since the callback returned true, and we're not specifically
          // canceling a polling loop, do it again!
          ? actually_setTimeout()
          
          // Otherwise, clean up and quit.
          : cleanup();
      };
      
      // Set that timeout!
      actually_setTimeout();
      
    } else if ( data.fn ) {
      // No callback passed. If force_mode (delay) is true, execute the data.fn
      // callback immediately, continuing any callback return-true polling loop.
      // If force_mode is false, execute the data.fn callback immediately but do
      // NOT continue a callback return-true polling loop. If force_mode is
      // undefined, simply clean up. Since data.fn was still defined, whatever
      // was supposed to happen hadn't yet, so return true.
      delay === undefined ? cleanup() : data.fn( delay === false );
      return true;
      
    } else {
      // Since no callback was passed, and data.fn isn't defined, it looks like
      // whatever was supposed to happen already did. Clean up and quit!
      cleanup();
    }
    
  };
  
})(jQuery);


/**
 * equalHight for Multicolumn Container
 */

(function($) {
	$(document).ready(function() {
		fixHeight.start();
	});
	var fixHeight = {
		elements : {},
		ie6 : ($.browser.msie && $.browser.version.substr(0, 1) < 7),
		start : function() {
			this.catchItems();
			this.forceElementHeight();
		},
		catchItems : function() {
			$('.multicolumnContainerFixHeight').each(function(containerIndex, container) {
				fixHeight.elements[containerIndex] = {};
				$(container).find('.column').each(function(columnIndex, column) {
					$(column).find('.columnItem').each(function(columnItemIndex, columnItem) {
						var $el = $(columnItem);
						if( typeof (fixHeight.elements[containerIndex][columnItemIndex]) === 'undefined') {
							fixHeight.elements[containerIndex][columnItemIndex] = {};
							fixHeight.elements[containerIndex][columnItemIndex]['el'] = [];
							fixHeight.elements[containerIndex][columnItemIndex]['elHeight'] = [];
						}
						fixHeight.elements[containerIndex][columnItemIndex]['el'].push($el);
						fixHeight.elements[containerIndex][columnItemIndex]['elHeight'].push($el.innerHeight());
					});
				});
			});
		},
		forceElementHeight : function() {
			$.each(this.elements, function(containerIndex, container) {
				$.each(container, function(columnItemIndex, columnItem) {
					if(columnItem.el.length > 1) {
						var height = columnItem['elHeight'].sort(fixHeight.sortNumber)[0], heightCss = fixHeight.ie6 ? {
							'height' : height + 'px'
						} : {
							'min-height' : height + 'px'
						};

						$.each(columnItem['el'], function(elementIndex, element) {
							$(element).css(heightCss);
						});
					}
				});
			});
		},
		sortNumber : function(a, b) {
			return b - a;
		}
	};
}(jQuery));
