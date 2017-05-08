var RESISTOR = RESISTOR || (function(){
    var resistorImg = new Image();
    resistorImg.src = "_view/images/resistor.png";
    var color1;
    var color2;
    var color3;
    var color4;
    var elementID
    return {
        init : function(arg1, arg2, arg3, arg4, args5) {
        	color1 = arg1;
        	color2 = arg2;
        	color3 = arg3;
        	color4 = arg4;
        	elementID = args5;
            
        },
        drawResistor : function() {
        	// Set up!
        	console.log(elementID);
        	console.log(color1);
        	console.log(color2);
        	console.log(color3);
        	console.log(color4);
            var canvas = document.getElementById(elementID);
            var ctx = a_canvas.getContext("2d");
            
            //function called to make sure image is loaded before drawing anything else
            resistorImg.onload = function() {
            	
                ctx.drawImage(resistorImg, 0, 0);
                
                ctx.fillStyle = color1;
                ctx.beginPath();
                ctx.fillRect(120, 7, 25, 66);
                ctx.closePath();
                ctx.fill();
                
                ctx.fillStyle = color2;
                ctx.beginPath();
                ctx.fillRect(170, 7, 25, 66);
                ctx.closePath();
                ctx.fill();
                
                ctx.fillStyle = color3;
                ctx.beginPath();
                ctx.fillRect(220, 7, 25, 66);
                ctx.closePath();
                ctx.fill();
                
                if(color4.localeCompare("none")){
                	ctx.fillStyle = color4;
                	ctx.beginPath();
                    ctx.fillRect(270, 7, 25, 66);
                    ctx.closePath();
                    ctx.fill();
                }
                
                
                

                ctx.fillStyle = "black";
                ctx.font = "30px Garamond";
                ctx.fillText(color1 + " " + color2 + " " + color3 + " " + color4,60,110);
            };

            

            
            
            
        }
    };
}());