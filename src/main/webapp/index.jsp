<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/fingerprint2.js"></script>
    <title></title>
  </head>
  <body onload="test()">
  	收集的设备数据：
  	<div id="details">
  	</div>
  	设备指纹信息：
  	<div id="fp">
  	</div>
  	消耗时间：
  	<div id="time">
  	</div>
  </body>
  <script type="text/javascript">
		  function test() {
		      var d1 = new Date();
		      var fp = new Fingerprint2();
		      fp.get(function(result, components) {
		        var d2 = new Date();
		        var timeString = "Time took to calculate the fingerprint: " + (d2 - d1) + "ms";
		        var details = "<strong>Detailed information: </strong><br />";
		        if(typeof window.console !== "undefined") {
		          console.log(timeString);
		          console.log(result);
		          for (var index in components) {
		            var obj = components[index];
		            var value = obj.value;
		            var line = obj.key + " = " + value.toString().substr(0, 100);
		            console.log(line);
		            details += line + "<br />";
		          }
		        }
		        document.getElementById("details").innerHTML=details;
		        document.getElementById("fp").innerHTML=result;
		        document.getElementById("time").innerHTML=timeString;
		      });
		  }
  </script>
</html>
