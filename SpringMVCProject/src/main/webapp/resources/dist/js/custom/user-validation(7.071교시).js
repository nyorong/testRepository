 
 $(function(){
	 
	 //회원가입 입력값 검증
	 
	 //아이디 입력값 확인 키보드 입력 이벤트
	 //keyup은 한글자 입력할때마다 실행됨
	 //f12 console창에서 입력하는거 확인가능
	 $("#user_id").on("keyup", function(){
		 //this는 #user_id를 가리킴
		 //console.log($(this).val())
		 
		 const userId = $(this).val();
	
		 
		 $.ajax({
			 type: "POST",
			 url: "/mvc/user/idCheck",
			 headers:{
				 "Content-Type": "application/json",
	                "X-HTTP-Method-Override": "POST"
			 },
			 datatype: "json",
			 data: userId, userPw ,
			 success: function(data){
				 //console.log(data);
				 if(data.confirm =="OK"){
					 $("#idChk").html("<b style='font-size=14px; color : green'> [ID 사용가능!]</b>");
					 
				 }else{
					 $("#idChk").html("<b style='font-size=14px; color : red'>[ID 중복!] </b>");
				 }
			 }
			 
		 });
	 });
	 
	 //회원가입 버튼 클릭 입네트
 	 $("#signup-btn").on("click", function(){
 		 
	  const id = $("#user_id").val();
      const pw = $("#password").val();
      const name = $("#user_name").val();
      const user = {
         userId: id,
            userPw: pw,
            userName: name
      };
      console.log(user);
      
      $.ajax({
         type: "POST",
         url: "/mvc/user",
            headers: {
                "Content-Type": "application/json",
                "X-HTTP-Method-Override": "POST"
            },
            dataType: "text",
            //데이터를json으로 스트링화
            data: JSON.stringify(user),
            success: function(result) {
               console.log("result: " + result);
               if(result === "joinSuccess") {
                  alert("회원가입 성공!");
                  self.location = "/mvc";
               }
            }
      })
		 
	 });
	 
 });
