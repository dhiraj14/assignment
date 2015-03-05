
function draw(){
alert("draw")
var canvas = document.getElementById("myCanvas");
var context = canvas.getContext("2d");
context.beginPath();
context.moveTo(250,250);
var i;
var y;
for (i = this.xmin; i <= this.xmax;i = i + this.step)
	{
      		y = parseInt((-(this.c) - (i * this.a)) / (this.b));
           	//alert(y);
      		context.lineTo(i,y);
      		context.stroke();       
	}
}





function parser1(line){
var x_index=line.indexOf("x");
var x_coefficient=line.substring(0,x_index);
var y_index=line.indexOf("y");
var y_coefficient=line.substring(x_index+2,y_index);
var constant=line.substring(y_index+2,line.indexOf("="));
var lineObject={a:x_coefficient,b:y_coefficient,c:constant,xmin:null,xmax:null,step:null,
draw:draw};
return lineObject;
}

