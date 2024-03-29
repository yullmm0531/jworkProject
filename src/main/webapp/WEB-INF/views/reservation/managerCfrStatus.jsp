<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>

<style>
	.fc-toolbar-chunk *{
	 float:left!important;
	 margin-left:14px!important;
	}
	.fc-daygrid-day-frame,.fc-timegrid-axis-frame-liquid,.fc-timegrid-axis{
	display:none;
	}
	  #cfrDetail tr td{
    	 height: 30px;
    }
</style>
    <meta charset='utf-8' />
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar-scheduler@6.1.4/index.global.min.js'></script>
   
    <script>
    document.addEventListener('DOMContentLoaded', function() {
    	  var calendarEl = document.getElementById('calendar');

    	  var calendar = new FullCalendar.Calendar(calendarEl, {
    		  schedulerLicenseKey: 'CC-Attribution-NonCommercial-NoDerivatives',
    		  expandRows: true,
    		  timeZone: 'local',
    		  selectable: true,
    		 slotMinTime: "09:00:00", // 최소시간
             slotMaxTime: "20:00:00", // 최대시간 (23시까지만 화면에 보여짐)
    	    initialView: 'resourceTimeGridDay',
    	    dateClick: function(info) {
                //alert('Clicked on: ' + info.dateStr); // 날짜 띄우는 것까지 성공 ㅅㅂ
                // change the day's background color just for fun
              /*   info.dayEl.style.backgroundColor = '#d6dfcc'; */
               // $("#reservationForm").modal("show");
              
            },
    	    customButtons:{
    	    	info:{
    	    		text:'회의실정보',
    	    		click:function(){
    	    			  $("#cfrInfo").modal("show");
    	    			 selectList();
    	    		}
            	},
    	    	reserve:{
    	    		 text:'회의실예약',
    	    		 click:function(event){
    	    			 
    	    			 $("#reservationForm").modal("show");
    	    			 selectList();
    	    		 }
    	    	}
    	    	
    	    	
    	    },
    	    headerToolbar: {
                left: 'info,reserve',
                center: 'prev,title,next',
               right:'today,listWeek'
            },
            
            buttonText: {
                prev:"<",
                next:">",
                today: "오늘",
                month:"월",
                week:"주",
                day:"일",
                list: "목록",
            },
            nowIndicator: true, // 현재 시간 마크
            navLinks:false, // 달력상의 날짜를 클릭할 수 있는지 여부, 클릭시 일 달력/주 달력으로 넘어감(Boolean, default:false/Demo)
            locale: 'ko',
            day:'numeric',
            navLinks: true,
            select:function(info){

            	$.ajax({
            		url:"adetail.cfr"
            	   ,data:{cfrName:info.resource.id}
            	   ,success:function(c){
            			$("#cfN").text(c.cfrName);
            			$("#cfrImg").attr("src",c.firstImg);
            			$("#cap").text(c.capacity);
            		
            			var equipment = c.equipment.split(","); 
            			$("#eq").text(equipment);
            	   },error:function(){
            		   
            		   console.log("ajax통신에러");
            	   }
            	   
            	});
            	
        		
        		
        		$("#cfrDetailView").modal("show");
        		
           	},
    	    resources: [
    	      $.ajax({
    	    		url:"list.acfrn",
    	    		success:function(list){
    	    			for(let i=0; i<list.length; i++){
    	    				calendar.addResource({
    	    				id:list[i].cfrName,
    	    				title:list[i].cfrName
    	    				})
    	    			}
    	    		}
    	    		
    	    	})
    	    ],
    	 
                 events: [
                 	<c:forEach var="c" items="${list}">
                 		<c:if test="${c.cfrName eq '1회의실'}">
                     	{ id: '${c.resNo}', resourceId: '${c.cfrName }', start: '${c.useDate} ${c.startTime}', end: '${c.useDate} ${c.endTime }', title: '${c.empName} / ${c.cfTitle} ', color: '#FF5B5B'},
                  		</c:if>
                     	<c:if test="${c.cfrName eq '2회의실'}">
                     	{ id: '${c.resNo}', resourceId: '${c.cfrName }', start: '${c.useDate} ${c.startTime}', end: '${c.useDate} ${c.endTime }', title: '${c.empName} / ${c.cfTitle}', color: '#f7C38C'},
                  		</c:if>
                     	<c:if test="${c.cfrName eq '3회의실'}">
                     	{ id: '${c.resNo}', resourceId: '${c.cfrName }', start: '${c.useDate} ${c.startTime}', end: '${c.useDate} ${c.endTime }', title: '${c.empName} / ${c.cfTitle}', color: '#FFFF7D'},
                  		</c:if>
                     	<c:if test="${c.cfrName eq '4회의실'}">
                     	{ id: '${c.resNo}', resourceId: '${c.cfrName }', start: '${c.useDate} ${c.startTime}', end: '${c.useDate} ${c.endTime }', title: '${c.empName} / ${c.cfTitle}', color: '#61Cf8F'},
                  		</c:if>
                     	<c:if test="${c.cfrName eq '5회의실'}">
                     	{ id: '${c.resNo}', resourceId: '${c.cfrName }', start: '${c.useDate} ${c.startTime}', end: '${c.useDate} ${c.endTime }', title: '${c.empName} / ${c.cfTitle}', color: '#8DD7EE'},
                  		</c:if>
                     	<c:if test="${c.cfrName eq '6회의실'}">
                     	{ id: '${c.resNo}', resourceId: '${c.cfrName }', start: '${c.useDate} ${c.startTime}', end: '${c.useDate} ${c.endTime }', title: '${c.empName} / ${c.cfTitle}', color: '#54A1DC'},
                  		</c:if>
                     	<c:if test="${c.cfrName eq '7회의실'}">
                     	{ id: '${c.resNo}', resourceId: '${c.cfrName }', start: '${c.useDate} ${c.startTime}', end: '${c.useDate} ${c.endTime }', title: '${c.empName} / ${c.cfTitle}', color: '#F0B6D8'},
                  		</c:if>
                     	
                    </c:forEach>
                 ]
                 
    	  })

    	  calendar.render();
    	});
		
    
 
    	
   
    </script>
  
  </head>
  
  <body>
   <jsp:include page="../common/menubar.jsp"/>
    <div style="width: 940px; margin:20px;">
        <h3>회의실 예약 현황</h3>
        <hr>
         <div id='calendar' style="margin:;">
         </div>
        
        <div>
           
    </div>
      
   </div>
	       <!--회의실 예약 모달 -->
    <div class="modal" id="reservationForm">
        <div class="modal-dialog">
        <div class="modal-content">
    
            <!-- Modal Header -->
            <div class="modal-header">
            <h4 class="modal-title">회의실 예약</h4>
            <button type="button" class="close" data-dismiss="modal" onclick=" $('#reservationForm').modal('hide');">&times;</button>
            </div>
    
            <!-- Modal body -->
            <div class="modal-body">
	        <form action="reserv.cfr" method="post">
                <input type="hidden" name="reservation" value="${loginUser.empNo}">
                    <table id="cfrResevation" style="width:800px; height:300px;">
                        <tr>
                            <th>회의실</th>
                            <td>
                           		<select name="cfrName" id="cfrName" required> 
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>예약자</th>
                            <td>${loginUser.empName}${loginUser.empNo}</td>
                            
                        </tr>
                        <tr>
                            <th>날짜</th>
                            <td><!-- <input class="datepicker" name="useDate" id="datepicker"required> -->
                            	<input type="date" name="useDate" id="useDate" onchange="selectDay($(this).val());" required>
                            </td>
                        </tr>
                        <tr>
                            <th>시간</th>
                          	<td>
                            <select name="startTime" onchange="endTimeShow($(this).val());" id="startTime" disabled required>
                          	    <option>- 시작시간 -</option>
                                <option value="09:00" >09:00</option>
                                <option value="10:00">10:00</option>
                                <option value="11:00">11:00</option>
                                <option value="12:00">12:00</option>
                                <option value="13:00">13:00</option>
                                <option value="14:00">14:00</option>
                                <option value="15:00">15:00</option>
                                <option value="16:00">16:00</option>
                                <option value="17:00">17:00</option>
                                <option value="18:00">18:00</option>
                            </select>
                            -
                            <select name="endTime" id="endTime" disabled required>
                                <option >- 종료시간 -</option>
                                <option value="10:00">10:00</option>
                                <option value="11:00">11:00</option>
                                <option value="12:00">12:00</option>
                                <option value="13:00">13:00</option>
                                <option value="14:00">14:00</option>
                                <option value="15:00">15:00</option>
                                <option value="16:00">16:00</option>
                                <option value="17:00">17:00</option>
                                <option value="18:00">18:00</option>
                                <option value="19:00">19:00</option>
                            </select>
                            </td>
                          <!--   <td><input type="text" name="startTime"  class="timepicker"  required>-<input type="text" class="timepicker" name="endTime" required></td> -->
                        </tr>
                        <tr>
                            <th>인원</th>
                            <td><input type="number" name="capacity" required></td>
                        </tr>
                        <tr>
                            <th>회의제목</th>
                            <td><input type="text" name="cfTitle"required></td>
                        </tr>
                    </table>
                    <button type="submit" onclick="return timeNeed();" class="btn btn-success btn-sm">예약하기</button>
               </form>
	      </div>
    
        </div>
        </div>
    </div>
      <!--회의실 정보 모달 -->
    <div class="modal" id="cfrInfo">
        <div class="modal-dialog">
        <div class="modal-content">
    
            <!-- Modal Header -->
            <div class="modal-header">
            <h4 class="modal-title">회의실 정보</h4>
            <button type="button" class="close" data-dismiss="modal" onclick=" $('#cfrInfo').modal('hide');">&times;</button>
            </div>
    
            <!-- Modal body -->
            <div class="modal-body">
                <div id="btn-area">
                	
                </div>
                <br>
                <div style="width:800px">
                    <table  style="width:800px; height:320px;"> 
                        <tr>
                            <th>회의실명</th>
                            <td id="inputCfrName"></td>
                        </tr>
                        <tr>
                            <th>회의실 이미지</th>
                            <td >
                                <div style="width: 320px;">
                                    <img src="" width="220px" height="170px" id="firstImg">
                                </div>
                            </td>
                            
                        </tr>
                        <tr>
                            <th>수용인원</th>
                            <td id="capacity"></td>
                        </tr>
                        <tr>
                            <th>회의장비</th>
                            <td id="equipment"></td>
                        </tr>
                    </table>

                </div>
            </div>
    
        </div>
        </div>
    </div>
      <script>
        function selectList(){
        	
        	$.ajax({
        		url:"alist.cfr",
            	success:function(list){
            		let value="";
            		let value2="";
            		for(var i=0; i<list.length; i++){
            			
            			value+="<button type='button' class='btn btn-sm btn-light cfrBtn'value='"
            					+list[i].cfrName+"' onclick='detailCfr($(this).val());'>" +list[i].cfrName
            					+"</button>";
            			
            			value2+="<option>"+list[i].cfrName+"</option>";
            			
            			
            		
            		}
            		
            		$("#btn-area").html(value);
            		$("select[name=cfrName]").html(value2);
            		$("#btn-area .btn")[0].click();
            		
            	},error:function(){
            		console.log("ajax통신실패");
            	}
            		
        	});
        	
        	
        	
        	
        }
        
        function detailCfr(cfrName){
        	
        	$.ajax({
        		url:"adetail.cfr"
        	   ,data:{cfrName:cfrName}
        	   ,success:function(c){
        			$("#inputCfrName").text(c.cfrName);
        			$("#firstImg").attr("src",c.firstImg);
        			$("#capacity").text(c.capacity);
        			var equipment = c.equipment.split(","); 
        			
        			$("#equipment").text(equipment);
        	   },error:function(){
        		   
        		   console.log("ajax통신에러");
        	   }
        	   
        	});
        	
        	
        }
         
     </script>
	    <script>
	   		 function timeNeed(){
				if($("select[name=startTime]").val() == "- 시작시간 -" || $("select[name=endTime]").val() == "- 종료시간 -"){
					alert("시작/종료시간을 입력하세요.");
					return false;
			}
	   		 }
	   		 
	   		 function endTimeShow(val){
	   			
	   			 $("#endTime").prop("disabled",false);
	   			 
	   			$("#endTime option").each(
						function(){    
							if($(this).val() <= val) {
								   $(this).prop("disabled",true);
								}
					    }
					);
	   			
	   			let cfrName= $("#cfrName").val();
	   			let useDate= $("#useDate").val();
	   			
	   			$.ajax({
	    			url:"time.cfr",
	    			data:{useDate:useDate,cfrName:cfrName},
	    			success:function(list){
	    				for(let i=0; i<list.length; i++){
	    					$("#endTime option").each(
	    							function(){    
	    								if($(this).val() >= list[i].startTime && $(this).val() <= list[i].endTime) {
	    									   $(this).prop("disabled",true);
	    									   
	    									}
	    								
	    								if(list[i].startTime >= val ){
	    									
	    									if($(this).val() >= list[i].startTime){
	    										
	    									  $(this).prop("disabled",true);
	    									}
	    								}
	    						    }
	    						);

	    				}
	    			},error:function(){
	    				console.log('날짜 조회용 ajax 통신 실패');
	    			}
	    			
	    		})
	    	}
	    	    	
	    	// 날짜선택제한
	    	$(function(){
	    		
	    		var now_utc = Date.now() // 지금 날짜를 밀리초로
	    		// getTimezoneOffset()은 현재 시간과의 차이를 분 단위로 반환
	    		var timeOff = new Date().getTimezoneOffset()*60000; // 분단위를 밀리초로 변환
	    		// new Date(today-timeOff).toISOString()은 '2022-05-11T18:09:38.134Z'를 반환
	    		var today = new Date(now_utc-timeOff).toISOString().substring(0, 10);
	    		 
	    		document.getElementById("useDate").setAttribute("min", today);
	    	
	    	})
	    	
	    	
	    	function selectDay(val){
	    		let cfrName= $("#cfrName").val();
	    		 $("#startTime").prop("disabled",false);
	    		$.ajax({
	    			url:"time.cfr",
	    			data:{useDate:val,cfrName:cfrName},
	    			success:function(list){
	    				for(let i=0; i<list.length; i++){
	    				
	    			
	    					$("#startTime option").each(
	    							function(){    
	    								if($(this).val() >= list[i].startTime && $(this).val() < list[i].endTime) {
	    									   $(this).prop("disabled",true);
	    									}
	    						    }
	    						);

	    					$("#endTime option").each(
	    							function(){    
	    								if($(this).val() > list[i].startTime && $(this).val() < list[i].endTime) {
	    									   $(this).prop("disabled",true);
	    									}
	    						    }
	    						);

	    				}
	    			},error:function(){
	    				console.log('날짜 조회용 ajax 통신 실패');
	    			}
	    			
	    		})
	    	}
	    	
	  
	    </script>
	     <div class="modal" id="cfrDetailView">
        <div class="modal-dialog">
        <div class="modal-content">
    
            <!-- Modal Header -->
            <div class="modal-header">
            <h4 class="modal-title">회의실 상세 정보</h4>
            <button type="button" class="close" data-dismiss="modal" onclick=" $('#cfrDetailView').modal('hide');">&times;</button>
            </div>
    
            <!-- Modal body -->
            <div class="modal-body">
                <table id="cfrDetail" style="width:600px;">
                    <tr >
                        <th >회의실명</th>
                        <td>
                            <div class="col-7">
                            	<span id="cfN"></span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>회의실 이미지</th>
                        <td>
                        	&nbsp; &nbsp;&nbsp;<img id="cfrImg"   width="230px" height="150px" readonly>
                        </td>
                    </tr>
                    <tr>
                        <th >인원</th>
                        <td>
                          <div class="col-4">
                         	 <span id="cap"></span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th >장비</th>
                        <td>
                           <div class="col-10">
                           	<span id="eq"></span>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
    
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-outline-success" data-dismiss="modal" onclick=" $('#cfrDetailView').modal('hide');">닫기</button>
            </div>
    
        </div>
        </div>
    </div>
  </body>
</html>