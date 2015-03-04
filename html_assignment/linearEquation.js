$(document).ready(function(){
	var obj;  
	$("#parse").click(function(){
	var line=$("#equation").val();        
	var pattern = /^[a-z0-9]*[x][+|-][a-z0-9]*[y][+|-][a-z0-9]*[=][0]$/i;
	alert("hello world");	
	if(pattern.test(line)) {	
	
	obj=parser1(line);
	
	
	$("#x-coefficient").val(obj.a);
	$("#y-coefficient").val(obj.b);
	$("#constant").val(obj.c);
	$("#coefficient").css('visibility','visible');
	$("#draw").click(function(){
	
	$("#graph").css('visibility','visible');
	obj.xmin=$("#x-min").val();
	obj.xmax=$("#x-max").val();
	obj.step=$("#step").val();
	obj.draw()	
		
	});
		
	}
});

	
});

