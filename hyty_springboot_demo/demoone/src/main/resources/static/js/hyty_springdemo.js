
	    	function queryuser(){
	    		$.ajax({
			        url: "http://127.0.0.1:8081/user/selecthead",
			        type: "post",
			        success: function (data) {
                        var str = "";
                        $("#table_id tbody").html("");
                        var json = eval("("+data+")");
                        if(json.result.toString()=="true"){
                        	
                        	var vae = json.value;
                        $.each(vae, function (index, item) {
                            str+='<tr>'+
                                '<td style="display:none;">' + vae[index].id + '</td>' + 
                                '<td>' + vae[index].code + '</td>' + 
                                '<td>' + vae[index].name + '</td>' +
                                '<td>' + vae[index].effectivedate + '</td>' +
                                '<td style="display:none;">' + vae[index].state + '</td>' +
                                '<td style="width: 50px;">&nbsp;<input type="button" value="修改" onclick="updateuser(this)"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="删除"  onclick="deleteuser(this)"/></td>' +
                                '</tr>';
                       });
                       
                        $("#table_id tbody").prepend(str);
                        
                        //阻止冒泡
						$("#table_id tbody td:last-child").click(function(event){
							event.cancelBubble=true;
							event.stopPropagation();
						});
						
                        }else{
                        	alert(json.msg);
                        }
			        }
			    });
	    	}
	    	
	    	function queryuserbyid(){
	    		var UID=$("#UID").val().toString();
	    		$.ajax({
			        url: "http://127.0.0.1:8081/user/selectid",
			        data:UID,
			        type: "post",
			        success: function (data) {
                        var str = "";
                        var json = eval("("+data+")");
                        if(json.result.toString()=="true"){
                        	alert(json.msg);
                        	var vae = json.value;
                        $.each(vae, function (index, item) {
                            str+='<tr>'+
                                '<td>' + vae[index].code + '</td>' + 
                                '<td>' + vae[index].name + '</td>' +
                                '<td>' + vae[index].effectivedate + '</td>' +
                                '<td style="display:none;">' + vae[index].state + '</td>' +
                                '</tr>';
                       });
                        $("#table1 tbody").prepend(str);
                        }else{
                        	alert(json.msg);
                        }
			        }
			    });
	    	}
	    	
	    	function deleteuser(obj){
	    		
	    		var v_user_tr = obj.parentNode.parentNode;
	    		v_user_tr.parentNode.removeChild(v_user_tr);
	    		var v_user_tds=v_user_tr.cells;
				var UID = v_user_tds[0].innerText;
			
				$.ajax({
			        url: "http://127.0.0.1:8081/user/deleteuser",
			        data:{id:UID},
			        type: "post",
			        success: function (data) {
                        var str = "";
                        var json = eval("("+data+")");
                        alert(json.msg);
			        }
			    });
	    	}
	    	
	    	function deleteuserbody(obj){
	    		var v_user_tr = obj.parentNode.parentNode;
	    		v_user_tr.parentNode.removeChild(v_user_tr);
	    		var v_user_tds=v_user_tr.cells;
				var UID = v_user_tds[0].innerText;
			
				$.ajax({
			        url: "http://127.0.0.1:8081/user/deleteuserbody",
			        data:{id:UID},
			        type: "post",
			        success: function (data) {
                        var str = "";
                        var json = eval("("+data+")");
                        alert(json.msg);
			        }
			    });
	    	}
	    	
	    	var v_user_tds = null;
	    	function updateuser(obj){
	    		
	    		var v_user_tr = obj.parentNode.parentNode;
	    	    v_user_tds=v_user_tr.cells;
	    	    var sysid=v_user_tds[0].innerText;
				var uid = v_user_tds[1].innerText;
				var uname=v_user_tds[2].innerText;
				var utime=v_user_tds[3].innerText;
				
				document.getElementById("sysId").value=sysid;
				document.getElementById("UserId").value=uid;
				document.getElementById("Username").value=uname;
				document.getElementById("Usertime").value=utime;
				$("#myModalUp").modal("show");
				
	    	}
	    	
	    	function saveuser(){
	    		var sysid=document.getElementById("sysId").value.toString();
	    		var uid = document.getElementById("UserId").value.toString();
				var uname=document.getElementById("Username").value.toString();
				var utime=document.getElementById("Usertime").value.toString();
				var adata={
					"id":sysid,
					"code":uid,
					"name":uname,
					"effectivedate":utime
				};
				var data=JSON.stringify(adata);
				
				$.ajax({
					type:"POST",
					contentType:"application/json",
					data:data,
			        url: "http://127.0.0.1:8081/user/insertuser",
			        success: function (data) {
			        	
			        	v_user_tds[1].innerText=uid;
						v_user_tds[2].innerText=uname;
						v_user_tds[3].innerText=utime;
						
                        var str = "";
                        var json = eval("("+data+")");
                        alert(json.msg);
                        $("#myModalUp").modal("hide");
			        }
			   });
	    	}
	    	
	    	$("#table_id tbody").on("click","tr",function() {
				var td = $(this).find("td");
				var Id = td.eq(0).text().toString();
				$.ajax({
			        url: "http://127.0.0.1:8081/user/selectbody",
			        type: "get",
			        data:{id:Id},
			        success: function (data) {
			        	$("#mymodal_tbody").html("");
                        var str = "";
                        var json = eval("("+data+")");
                        if(json.result.toString()=="true"){
                        	
                        	var vae = json.value;
                        $.each(vae, function (index, item) {
                            str+='<tr>'+
                            	'<td style="display:none;">' + vae[index].id + '</td>' +
                                '<td>' + vae[index].adreess + '</td>' + 
                                '<td>' + vae[index].worktype + '</td>' +
                                '<td>' + "" + '</td>' +
                                '<td>' + vae[index].idnumber + '</td>' +
                                '<td>' + ""+ '</td>' +
                                '<td>' + "" + '</td>' +
                                '<td style="width: 50px;">&nbsp;&nbsp;<input type="button" value="删除"  onclick="deleteuserbody(this)"/></td>' +
                                '</tr>';
                       });
                        $("#table_modal tbody").prepend(str);
                        
                        //阻止冒泡
						$("#table_modal tbody td:last-child").click(function(event){
							event.cancelBubble=true;
							event.stopPropagation();
						});
                        
                        }else{
                        	alert(json.msg);
                        }
			        }
			    });
				$("#myModal").modal("show");
			});
			
