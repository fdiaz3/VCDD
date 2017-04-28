var RESISTOR = RESISTOR || (function(){
    var _args = {}; // private
    var resistorImg = new Image();
    resistorImg.src = "_view/images/resistor.png";

    return {
        init : function(Args) {
            _args = Args;
            // some other initialising
        },
        drawResistor : function() {
        	// Set up!
            var a_canvas = document.getElementById("aResistor");
            var ctx = a_canvas.getContext("2d");
            
            ctx.drawImage(resistorImg, 0, 0);
            
            //ctx.fillStyle = "gold";
            //ctx.shadowBlur=20;
            //ctx.shadowColor="black";
            
            ctx.fillStyle = _args[0];
            ctx.beginPath();
            ctx.fillRect(125, 5, 25, 70);
            ctx.closePath();
            ctx.fill();
            
            ctx.fillStyle = _args[1];
            ctx.beginPath();
            ctx.fillRect(175, 5, 25, 70);
            ctx.closePath();
            ctx.fill();
            
            ctx.fillStyle = _args[2];
            ctx.beginPath();
            ctx.fillRect(225, 5, 25, 70);
            ctx.closePath();
            ctx.fill();
            
            ctx.fillStyle = _args[3];
            ctx.beginPath();
            ctx.fillRect(275, 5, 25, 70);
            ctx.closePath();
            ctx.fill();
            

            ctx.fillStyle = "black";
            // Write "Hello, World!"
            ctx.font = "30px Garamond";
            ctx.fillText(_args[0] + " " + _args[1] + " " + _args[2] + " " + _args[3],15,175);
            
            
            
        }
    };
}());