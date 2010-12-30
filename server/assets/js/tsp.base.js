/*
    The Switchable Painting
    main.css
    JS code.
    
    Author: Alex Michael, 2010.
 
*/

/***************************
 * ANIMATOR WHEN ANALYSING *
 ***************************/

var AnalyseAnimator = function(p, i, cbacks) {
    
    if (cbacks) {
        if (cbacks.onStop) {
            this.onStop = cbacks.onStop;
        }
        if (cbacks.onStart) {
            this.onStart = cbacks.onStart;
        }
    }
    
    var createCanvas = function() {
        var canvas = $("<canvas>")
                    .attr("id", "animation-canvas-overlay")
                    .attr("width", image.width())
                    .attr("height", image.height());
        canvas.css({"left": image.position().left,
                    "top": image.position().top,
                    "position": "absolute",
                    "z-index": 2});
        parent.append(canvas);
        return document.getElementById("animation-canvas-overlay");
    }
    
    var render = function() {
        canvas.width = canvas.width;
        for (var i = 0; i < 100; i++) {
            var rx = parseInt(Math.random() * image.width());
            var ry = parseInt(Math.random() * image.height());
            ctx.fillStyle = "rgba(236,28,28, 0.7)";  
            ctx.fillRect(rx, ry, 8, 8);    
        }
        if (_play_) {
            if (_iterative_) {
                _iterations_--;
            }
            if ((!_iterative) || (_iterative_ && _iterations_ >= 0)) {
                window.setTimeout(render, 250);
            } else {
                _play_ = false;
                this.onStop();
            }
        }
    }
    
    this.start = function(iterations) {
        if (iterations) {
            _iterations_ = iterations;
            _iterative_ = true;
        }
        _play_ = true;
        this.onStart();
        render();
    }
    
    this.stop = function() {
        _play_ = false;
    }
    
    this.onStart = function() {;}
    this.onStop = function() {;}
    
    /* Initialization */  
    var parent = $(p);
    var image = $(i);
    var canvas = createCanvas();
    var ctx = canvas.getContext("2d");
    var _play_ = false;
    var _timerID_ = -1;
    var _iterations_ = -1;
    var _iterative_ = false;
    
};

/*******************
 * IFRAME UPLOADER *
 *******************/

var Uploader = function(f) {
   
    this.upload = function() {
        if ($("#upload-target")[0] == null) {
            form.append(frame);
        }
        form.submit();
        window.setTimeout(poll, 100);
    };
    
    this.reset = function() {
        frame.contents().empty();
        this.progressBar.reset();
        POLL_INDEX = 0;
    };
    
    var poll = function() {
        var chunks = frame.contents().find("body span");
        if (chunks[0] === null || chunks.length === POLL_INDEX) {
            window.setTimeout(poll, 100);
            return;
        }
        var currentChunk = chunks[POLL_INDEX];
        POLL_INDEX++;
        var jsonResponse = $.parseJSON($(currentChunk).html());
        if (jsonResponse.success) {
            if (jsonResponse.last) {
                _formUploader_.progressBar.status("ok");
                 $("#upload-success-controls").show();
            }
        } else {
            _formUploader_.progressBar.status("fail");
            $("#upload-fail-controls").show();
        }
        _formUploader_.progressBar.text(jsonResponse.next_msg);
        if (!jsonResponse.last) {
            window.setTimeout(poll, 100);
        }
    };
    
    var frame = $("<iframe>")
                .css("display", "none")
                .attr({"id": "upload-target",
                       "name": "upload-target",
                       "src": "#"});
    
    this.progressBar = {
        elem: $("#upload-bar"),
        _text: "",
        text: function(msg) {
            if (msg) {
                this._status = msg;
                this.elem.html(msg);
                return this;
            } else {
                return this._status;
            }
        },
        status: function(klass) {
            this.elem.removeClass("ok fail");
            this.elem.addClass(klass);
            return this;
        },
        reset: function() {
            this.elem.removeClass("ok fail");            
            this.text("Uploading your painting..");
        }
    };
    
    var form = $(f);   
    form.submit({me: this}, function(e) {
        var $this = $(this);
        DOM.transition($this.find("#upload-controls"), e.data.me.progressBar.elem);
        $this.attr("target", frame.attr("name")); 
    });
   
   var POLL_INDEX = 0;
}

/*******************
 * DOM manipulator *
 *******************/

var DOM = new function() {
    
    this.transition = function(from, to) {
        $(from).fadeOut(100, function() {
            $(to).fadeIn(100);
        });
    };
    
    this.init = function(){ 
        $("#upload-painting-container form").submit(function() {
            $("#upload-painting-container form").attr("target","upload-target");
        });

        $("#show-upload-painting-btn").click(function(){
            DOM.transition($(this), $("#upload-painting-container"));
        });

        $("#cancel-upload-btn").click(function() {
            DOM.transition($("#upload-painting-container"), $("#show-upload-painting-btn"));
        });

        $("#upload-painting-btn").click(function(){
            _formUploader_.upload();
        });
        
        $("#upload-analysis-btn").click(function(){
            jQuery.facebox(function() { 
                jQuery.post('/api/analysis/' + paintingID , function(data) {
                    jQuery.facebox(data);
                });
            });
        });

        $("#upload-another-painting-btn").click(function(){
            $("#upload-bar").fadeOut(0);
            DOM.transition($("#upload-success-controls"), $("#upload-controls"));
            _formUploader_.reset();
        });

        $("#upload-fail-painting-btn").click(function(){
            $("#upload-bar").fadeOut(0);
            DOM.transition($("#upload-fail-controls"), $("#upload-controls"));
            _formUploader_.reset();
        });        
    };    
    
}

/*************
 * INIT CODE *
 *************/

_formUploader_ = new Uploader($("#upload-painting-container form"));
DOM.init();