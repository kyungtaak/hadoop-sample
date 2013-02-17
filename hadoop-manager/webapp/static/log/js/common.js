
	/* ============================================================
 * bootstrap-dropdown.js v2.2.1
 * http://twitter.github.com/bootstrap/javascript.html#dropdowns
 * ============================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============================================================ */
!function ($) {

  "use strict"; // jshint ;_;


 /* DROPDOWN CLASS DEFINITION
  * ========================= */

  var toggle = '[data-toggle=dropdown]'
    , Dropdown = function (element) {
        var $el = $(element).on('click.dropdown.data-api', this.toggle)
        $('html').on('click.dropdown.data-api', function () {
          $el.parent().removeClass('open')
        })
      }

  Dropdown.prototype = {

    constructor: Dropdown

  , toggle: function (e) {
      var $this = $(this)
        , $parent
        , isActive

      if ($this.is('.disabled, :disabled')) return

      $parent = getParent($this)

      isActive = $parent.hasClass('open')

      clearMenus()

      if (!isActive) {
        $parent.toggleClass('open')
        $this.focus()
      }

      return false
    }

  , keydown: function (e) {
      var $this
        , $items
        , $active
        , $parent
        , isActive
        , index

      if (!/(38|40|27)/.test(e.keyCode)) return

      $this = $(this)

      e.preventDefault()
      e.stopPropagation()

      if ($this.is('.disabled, :disabled')) return

      $parent = getParent($this)

      isActive = $parent.hasClass('open')

      if (!isActive || (isActive && e.keyCode == 27)) return $this.click()

      $items = $('[role=menu] li:not(.divider) a', $parent)

      if (!$items.length) return

      index = $items.index($items.filter(':focus'))

      if (e.keyCode == 38 && index > 0) index--                                        // up
      if (e.keyCode == 40 && index < $items.length - 1) index++                        // down
      if (!~index) index = 0

      $items
        .eq(index)
        .focus()
    }

  }

  function clearMenus() {
    $(toggle).each(function () {
      getParent($(this)).removeClass('open')
    })
  }

  function getParent($this) {
    var selector = $this.attr('data-target')
      , $parent

    if (!selector) {
      selector = $this.attr('href')
      selector = selector && /#/.test(selector) && selector.replace(/.*(?=#[^\s]*$)/, '') //strip for ie7
    }

    $parent = $(selector)
    $parent.length || ($parent = $this.parent())

    return $parent
  }


  /* DROPDOWN PLUGIN DEFINITION
   * ========================== */

  $.fn.dropdown = function (option) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('dropdown')
      if (!data) $this.data('dropdown', (data = new Dropdown(this)))
      if (typeof option == 'string') data[option].call($this)
    })
  }

  $.fn.dropdown.Constructor = Dropdown


  /* APPLY TO STANDARD DROPDOWN ELEMENTS
   * =================================== */

  $(document)
    .on('click.dropdown.data-api touchstart.dropdown.data-api', clearMenus)
    .on('click.dropdown touchstart.dropdown.data-api', '.dropdown form', function (e) { e.stopPropagation() })
    .on('click.dropdown.data-api touchstart.dropdown.data-api'  , toggle, Dropdown.prototype.toggle)
    .on('keydown.dropdown.data-api touchstart.dropdown.data-api', toggle + ', [role=menu]' , Dropdown.prototype.keydown)

}(window.jQuery);

	if(typeof String.prototype.trim !== 'function') {
	  String.prototype.trim = function() {
	    return this.replace(/^\s+|\s+$/g, ''); 
	  }
	}
	
    $(function() {
        var fields = [
			"abnormals",
			"attachcnt",
			"attaches",
			"body",
			"bodyPath",
			"cc",
			"documentyn",
			"favorite",
			"from",
			"header",
			"htmlType",
			"ipfrom",
			"ipto",
			"messageUsers",
			"msghost",
			"msgkey",
			"msgType",
			"msgurl",
			"outyn",
			"receivercnt",
			"receiveroutcnt",
			"senttime",
			"size",
			"subject",
			"to"
        ];
        
        var fieldTypes = {
        		"subject" : "string",
        		"body" : "string",
        		"attach" : "string",
        		"attachCnt" : "int",
        		"receiver" : "string",
        		"sender" : "string",
        		"ip" : "string"
        };
        
        var operator = [
			"and",
			"or",
			"not",
			"&",
			"|"
		];
        
        var condition = [
			"=",
			"!=",
			"in"
		];
        
        var brace = [
			"(",
			")"
		];
        
        var termTypes = [];
        var bracesStack = [];
        
        function split( val ) {
            return val.split( /\s+/ );
        }
        
        function inArray(term, array){
        	for(var i = 0 ; i < array.length ; i++){
        		if(term == array[i])
        			return true;
        	}
        	
        	return false;
        }
        
        function analyzeToken(term){
        	var terms = split(term);
        	var tokens = [];
        	var startPos = 0;
        	
        	for(var i = 0 ; i < terms.length ; i++){
        		var token = {};
        		var pos = {};
        		pos.startPos = term.indexOf(terms[i], startPos);
        		startPos = pos.startPos + terms[i].length;
        		
        		if(inArray(terms[i], fields)){
        			token.value = terms[i];
        			token.type = 'fields';
        			token.pos = pos;
        			
        			tokens.push(token);
        		}else if(inArray(terms[i], operator)){
        			token.value = terms[i];
        			token.type = 'operator';
        			token.pos = pos;
        			
        			tokens.push(token);
        		}else if(inArray(terms[i], condition)){
        			token.value = terms[i];
        			token.type = 'condition';
        			token.pos = pos;
        			
        			tokens.push(token);
        		}else if(inArray(terms[i], brace)){
        			token.value = terms[i];
        			token.type = 'brace';
        			token.pos = pos;
        			
        			tokens.push(token);
        		}else {
        			//기존 타입도 value일 경우 value가 연장이되는 상태이므로 하나로 합친다.
        			if(i > 0 && terms[i] != '' && tokens[tokens.length - 1].type == 'value'){
        				token = tokens[tokens.length - 1];
        				token.value += ' ' + terms[i];
        				token.pos.endPos = pos.endPos;
        			}else{
	        			token.value = terms[i];
	        			token.type = 'value';
	        			token.pos = pos;
	        			
        				tokens.push(token);
        			}
        		}
        	}
        	
        	return tokens;
        }
        
        $( "#search" )
        	.bind( "keydown", function( event ) {
	            if ( event.keyCode === $.ui.keyCode.TAB &&
	                    $( this ).data( "autocomplete" ).menu.active ) {
	                event.preventDefault();
	            }
	        }).bind( "keyup", function(event){
	        	if( event.keyCode >= 37 && event.keyCode < 40){
	            	console.log("menu.active >>>" + $( this ).data( "autocomplete" ).menu.active);
	            	if($( this ).context.value.length > 0 && !$( this ).data( "autocomplete" ).menu.active){
		            	$( this ).data( "autocomplete" ).search();
	            	}
	            }
	        }).bind( "focus", function( event ) {
	        	$( this ).data( "autocomplete" ).search();
	        }).autocomplete({
            source: function(request, response){
            	var term = request.term;
            	var terms = analyzeToken(term);
            	var lastTerm = [];
            	
            	var curPos = $( "#search" )[0].selectionStart;
            	var strLength = term.length;
            	
        		//curPos 는 현재 textarea에서 커서의 위치이다. 만일 커서의 위치가 텍스트의 길이보다 작다면 식 작성중 다른 위치로 옮겨 놓은 것이 된다.
        		//따라서 이에 맞는 자동완성 기능을 작성할 수 있도록 보정해 줘야한다.            	
            	if(curPos < strLength){
            		for(var i = 0 ; i < terms.length ; i++){
            			if(terms[i].pos.startPos > curPos){
            				if(terms[i - 1].type == 'fields'){
            					console.log(">>>>");
            					var filter = terms[i - 1].value.substr(0, curPos - terms[i - 1].pos.startPos);
            					console.log(filter);
            					lastTerm = jQuery.grep(fields, function(n, i){
	      	          				  return n.indexOf(filter) == 0;
	      	          			});
            					
            					response(lastTerm);
            				}else if(terms[i - 1].type == 'condition')
            					response(condition);
            				else if(terms[i - 1].type == 'operator')
            					response(operator);
            				
            				return;
            			}
            		}
            	}
            	
            	if(request.term.length > 0 && terms.length >= 1){
            		var lastTermIndex = terms.length - 1;
            		
            		if(terms.length == 1 ){
            			lastTerm = jQuery.grep(fields, function(n, i){
	          				  return n.indexOf(terms[lastTermIndex].value) == 0;
	          			});
            			
            			termTypes.push('field');
            		}else if(terms[lastTermIndex - 1].type == 'fields'){
            			lastTerm = jQuery.grep(condition, function(n, i){
            				  return n.indexOf(terms[lastTermIndex].value) == 0;
            			});
            		}else if(terms[lastTermIndex - 1].type == 'operator'){
            			lastTerm = fields.concat('(');
            			
            			lastTerm = jQuery.grep(lastTerm, function(n, i){
          				  return n.indexOf(terms[lastTermIndex].value) == 0;
          				});
            		}else if(terms[lastTermIndex - 1].type == 'condition'){
            			//condition 다음은 value가 와야 한다. value에는 현재 제시어가 없다.
            			lastTerm = [];
        			}else if(terms[lastTermIndex - 1].type == 'brace'){
        				//TODO: 추후에 brace 제어를 수행해야함.
        				if(terms[lastTermIndex - 1].value == '('){
        					lastTerm = fields;
        					
        					bracesStack.push('(');
        				}else if(terms[lastTermIndex - 1].value == ')'){
        					lastTerm = operator;
        					
        					braceStack.push(')');
        				}
        			}else{
        				var lastValue = terms[lastTermIndex].value;
        				
        				if(lastValue == ''){
        					lastTerm = operator.concat(brace);
            				
            				lastTerm = jQuery.grep(lastTerm, function(n, i){
               				  return n.indexOf(lastValue) == 0;
               				});
        				}
        			}
            	}else{
            		lastTerm = fields.concat(['(']);
            	}
            	
            	response(lastTerm);
            	return;
            },
            focus: function() {
                return false;
            },
            select: function( event, ui ) {
            	var terms = split(this.value);
            	
            	//term의 길이가 1인 경우에는 그 자체가 완성된 텀이다. 이 경우는 마지막 텀을 치환해서는 안되고 그 뒤에 붙여야한다.
            	if(terms[terms.length - 1] == ')' || terms[terms.length - 1] == '(' || terms[terms.length - 1] == '='){
            		terms[terms.length] = ui.item.value;
            	}else
            		terms[terms.length - 1] = ui.item.value;
            	
                this.value = terms.join(' ');
                return false;
            },
            minLength : 0
        });
    });



//스위치 on/off 
(function ($) {
    var methods = {
        init: function (options) {
            var settings = {
                cssPrefix: 'ss-',
                useAnimation: true,
                toggled: function () { },
                toggledOn: function () { },
                toggledOff: function () { }
            };

            if (options) {
                $.extend(settings, options);
            }

            this.each(function () {
                var self = $(this);
                var div = $('<a>');

                self.bind('ss-toggle', function () {
                    if (self.is(':checked')) {
                        self.removeAttr('checked');
                        settings.toggledOff(self);
                    } else {
                        self.attr('checked', 'checked');
                        settings.toggledOn(self);
                    }
                    self.trigger('ss-update');
                    settings.toggled(self);
                });

                self.bind('ss-toggleOn', function () {
                    self.attr('checked', 'checked');
                    self.trigger('ss-update');
                    settings.toggledOn(self);
                    settings.toggled(self);
                });

                self.bind('ss-toggleOff', function () {
                    self.removeAttr('checked');
                    self.trigger('ss-update');
                    settings.toggledOff(self);
                    settings.toggled(self);
                });

                self.bind('ss-update', function (o, disableAnimation) {
                    if (self.is(':checked')) {
                        $('span:eq(0)', div).show(settings.useAnimation && !disableAnimation ? 100 : 0);
                        $('span:eq(1)', div).animate({ left: div.width() - $('span:eq(1)', div).outerWidth(true) + 'px' }, settings.useAnimation && !disableAnimation ? 100 : 0);
                    } else {
                        $('span:eq(0)', div).hide(settings.useAnimation && !disableAnimation ? 100 : 0);
                        $('span:eq(1)', div).animate({ left: '0px' }, settings.useAnimation && !disableAnimation ? 100 : 0);
                    }
                });

                self.after(
					div.attr('class', self.attr('class'))
						.append($('<span>').addClass(settings.cssPrefix + 'on'))
						.append($('<span>').addClass(settings.cssPrefix + 'slider'))
						.click(function () {
						    self.trigger('ss-toggle');
						    return false;
						})
				)

                self.trigger('ss-update', true);
                self.hide();
            })
        },
        toggle: function () {
            $(this).trigger('ss-toggle');
        },
        toggleOn: function () {
            $(this).trigger('ss-toggleOn');
        },
        toggleOff: function () {
            $(this).trigger('ss-toggleOff');
        }
    };


    $.fn.slickswitch = function (method) {
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist');
        }
    };
})(jQuery);


$(function() {
	$('#switch1').slickswitch();
	$('#switch2').slickswitch({
		toggledOn: function() { $('#switch2status').text('On') },
		toggledOff: function() { $('#switch2status').text('Off') }
	});
});

!function ($) {

  "use strict"; // jshint ;_;


 /* MODAL CLASS DEFINITION
  * ====================== */

  var Modal = function (element, options) {
    this.options = options
    this.$element = $(element)
      .delegate('[data-dismiss="modal"]', 'click.dismiss.modal', $.proxy(this.hide, this))
    this.options.remote && this.$element.find('.modal-body').load(this.options.remote)
  }

  Modal.prototype = {

      constructor: Modal

    , toggle: function () {
        return this[!this.isShown ? 'show' : 'hide']()
      }

    , show: function () {
        var that = this
          , e = $.Event('show')

        this.$element.trigger(e)

        if (this.isShown || e.isDefaultPrevented()) return

        this.isShown = true

        this.escape()

        this.backdrop(function () {
          var transition = $.support.transition && that.$element.hasClass('fade')

          if (!that.$element.parent().length) {
            that.$element.appendTo(document.body) //don't move modals dom position
          }

          that.$element.show()

          if (transition) {
            that.$element[0].offsetWidth // force reflow
          }

          that.$element
            .addClass('in')
            .attr('aria-hidden', false)

          that.enforceFocus()

          transition ?
            that.$element.one($.support.transition.end, function () { that.$element.focus().trigger('shown') }) :
            that.$element.focus().trigger('shown')

        })
      }

    , hide: function (e) {
        e && e.preventDefault()

        var that = this

        e = $.Event('hide')

        this.$element.trigger(e)

        if (!this.isShown || e.isDefaultPrevented()) return

        this.isShown = false

        this.escape()

        $(document).off('focusin.modal')

        this.$element
          .removeClass('in')
          .attr('aria-hidden', true)

        $.support.transition && this.$element.hasClass('fade') ?
          this.hideWithTransition() :
          this.hideModal()
      }

    , enforceFocus: function () {
        var that = this
        $(document).on('focusin.modal', function (e) {
          if (that.$element[0] !== e.target && !that.$element.has(e.target).length) {
            that.$element.focus()
          }
        })
      }

    , escape: function () {
        var that = this
        if (this.isShown && this.options.keyboard) {
          this.$element.on('keyup.dismiss.modal', function ( e ) {
            e.which == 27 && that.hide()
          })
        } else if (!this.isShown) {
          this.$element.off('keyup.dismiss.modal')
        }
      }

    , hideWithTransition: function () {
        var that = this
          , timeout = setTimeout(function () {
              that.$element.off($.support.transition.end)
              that.hideModal()
            }, 500)

        this.$element.one($.support.transition.end, function () {
          clearTimeout(timeout)
          that.hideModal()
        })
      }

    , hideModal: function () {
        var that = this
        this.$element.hide()
        this.backdrop(function () {
          that.removeBackdrop()
          that.$element.trigger('hidden')
        })
      }

    , removeBackdrop: function () {
        this.$backdrop.remove()
        this.$backdrop = null
      }

    , backdrop: function (callback) {
        var that = this
          , animate = this.$element.hasClass('fade') ? 'fade' : ''

        if (this.isShown && this.options.backdrop) {
          var doAnimate = $.support.transition && animate

          this.$backdrop = $('<div class="modal-backdrop ' + animate + '" />')
            .appendTo(document.body)

          this.$backdrop.click(
            this.options.backdrop == 'static' ?
              $.proxy(this.$element[0].focus, this.$element[0])
            : $.proxy(this.hide, this)
          )

          if (doAnimate) this.$backdrop[0].offsetWidth // force reflow

          this.$backdrop.addClass('in')

          if (!callback) return

          doAnimate ?
            this.$backdrop.one($.support.transition.end, callback) :
            callback()

        } else if (!this.isShown && this.$backdrop) {
          this.$backdrop.removeClass('in')

          $.support.transition && this.$element.hasClass('fade')?
            this.$backdrop.one($.support.transition.end, callback) :
            callback()

        } else if (callback) {
          callback()
        }
      }
  }


 /* MODAL PLUGIN DEFINITION
  * ======================= */

  var old = $.fn.modal

  $.fn.modal = function (option) {
    return this.each(function () {
      var $this = $(this)
        , data = $this.data('modal')
        , options = $.extend({}, $.fn.modal.defaults, $this.data(), typeof option == 'object' && option)
      if (!data) $this.data('modal', (data = new Modal(this, options)))
      if (typeof option == 'string') data[option]()
      else if (options.show) data.show()
    })
  }

  $.fn.modal.defaults = {
      backdrop: true
    , keyboard: true
    , show: true
  }

  $.fn.modal.Constructor = Modal


 /* MODAL NO CONFLICT
  * ================= */

  $.fn.modal.noConflict = function () {
    $.fn.modal = old
    return this
  }


 /* MODAL DATA-API
  * ============== */

  $(document).on('click.modal.data-api', '[data-toggle="modal"]', function (e) {
    var $this = $(this)
      , href = $this.attr('href')
      , $target = $($this.attr('data-target') || (href && href.replace(/.*(?=#[^\s]+$)/, ''))) //strip for ie7
      , option = $target.data('modal') ? 'toggle' : $.extend({ remote:!/#/.test(href) && href }, $target.data(), $this.data())

    e.preventDefault()

    $target
      .modal(option)
      .one('hide', function () {
        $this.focus()
      })
  })

}(window.jQuery);