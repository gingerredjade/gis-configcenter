/*!
* base.js content v1.0
*
* Copyright 2015
*
* Designed and built by JiangHongyu
*/


/** 一个人的寂寞-单例模式（单体模式，只允许实例化一次的对象类）
* 1.提供一个命名空间，常用来定义命名空间，用它来约束每个人定义的变量解决冲突问题
* 2.通过单例模式来管理library库的各个模块
* 3.管理静态变量
* 
*/


/** 创建一个小型代码库
* 
* 惰性载入单例（即延迟创建单例对象形式）
* 
*/
var LazyUtil = (function(){
	
	// 单例实例引用
	var _instance = null;
	
	// 单例
	function Qbff(){
		/* 这里定义私有属性和方法 */
		
		// 私有变量
		var conf = {
			MAX_NUM : 100,
			MIN_NUM : 1,
			COUNT : 1000
		};
		
		var regularExpress = {
				"usernameRe" : /[^\w\u4e00-\u9fa5]/g, // 含有非法字符
				"re_number" : /[^\d]/g, // 全局匹配非数字的字符
				"re_letter" : /[^a-zA-Z]/g, // 全局匹配非字母的字符
				"emailReg" : /^[A-Za-z\d]+[A-Za-z\d\-_\.]*@([A-Za-z\d]+[A-Za-z\d\-]*\.)+[A-Za-z]{2,4}$/, // 邮箱验证
				"postcodeReg" : /^[1-9]\d{5}$/, // 邮编验证
				"ipReg" : /^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/, // IPV4地址验证
				"chinese_ideographReg" : /^[\u4E00-\u9FA5]+5/, // 汉字验证
				"integerReg" : /^-?\d+$/, // 整数验证
				"positive_integerReg" : /^[1-9]+\d*$/, // 正整数验证
				"negative_integerReg" : /^-{1}\d+$/, // 负整数验证
				"unnegative_integerReg" : /^\d+$/, // 非负整数验证
				"characterReg" : /^[a-zA-Z]+$/, // 英文字母验证
				"mobilephoneReg" : /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/, // 手机号码验证
				"phoneReg" : /^\d{3}\-\d{8}$|^\d{4}\-\d{7}$/, // 电话号码验证
				"dateReg" : /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/, // 日期（YYYY-MM-DD）验证
				"usernameReg" : /^[a-zA-Z]\w{3,16}$/, // 常用用户名验证(字母开头，允许4-16字节，允许字母数字下划线中文)
				"passwdReg" : /^[a-zA-Z][_a-zA-Z0-9]{3,10}$/, // 常用密码验证(以字母开头，长度4-10之间，只能包含字母、数字和下划线)
				"HexReg" : /^#?([a-f0-9]{6}|[a-f0-9]{3})$/, // 十六进制值
				"URLReg" : /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/ // URL
		};
		
		return {
			// 返回取值器对象，取值器方法如下
			get : function(name){
				return conf[name] ? conf[name] : null;
			},
			// 正则检测
			RegularExpression : function(content){
				return regularExpress[content] ? regularExpress[content] : null;
			},
			// 公用模块			
			Util : {
				// 自动修改破损图像
				util_imgrecovery : function(){
					$('img').on('error', function(){
						$(this).prop('src','img/broken.png');
					});
				},
				// 预加载图片 LazyQbff().Util.util_preloadpics('img/broken.png','img/broken1.png');
				util_preloadpics : function(){
					for(var i = 0; i < arguments.length; i++){
						$('<img>').attr('src',arguments[i]);
					}
				},
				util_exitsystem : function(username, password){
					$("li.exit-system").on("click",function(){
		        		$.ajax({
		        			type:'POST',
		        			url:'logoutAction.action',
		        			data:{"username":username, "password":password},
		        			cache:false,
		        			success:function(){
		        				window.location.href = "index.jsp";
		        			},
		        			error:function(){
		        				alert("退出系统失败...");
		        			}
		        		});
		        	});
				},
				// 实时显示当前时间
				util_getnowtime : function(){
					var date = new Date(); // 获取日期对象
					/* 获取年、月、日、时、分、秒，本地系统的时间 */
					return date.getFullYear() + "年"
							+ (date.getMonth() + 1) + "月"
							+ date.getDate() + "日"
							+ ""
							+ date.getHours() + "时"
							+ date.getMinutes() + "分"
							+ date.getSeconds() + "秒";
					
				},
				// 计算时间差
				util_timediscrepant : function(dateStartOri, dateEndOri){
					// 将时间字符串转化成时间对象
					var dateStart = new Date(Date.parse(dateStartOri.replace(/-/g,"/")));
					var dateEnd = new Date(Date.parse(dateEndOri.replace(/-/g,"/")));
					
					// 时间差的毫秒数
					var discrepantMilliseconds = dateEnd.getTime() - dateStart.getTime(); 
					// 计算出相差天数
					var discrepantDays = Math.floor(discrepantMilliseconds/(24*3600*1000));
					/*
					* 计算出相差小时数
					* 
					* 1.计算相差小时数
					* 2.计算天数后剩余的毫秒数
					*/
					var millisecondsForHour = discrepantMilliseconds%(24*3600*1000);
					var discrepantHours = Math.floor(millisecondsForHour/(3600*1000));
		
					/*
					* 计算出相差分钟数
					* 
					* 1.计算相差分钟数
					* 2.计算小时数后剩余的毫秒数
					*/
					var miliisesecondsForMin = millisecondsForHour%(3600*1000);
					var discrepantMins = Math.floor(miliisesecondsForMin/(60*1000));
		
					/*
					* 计算出相差秒数
					* 
					* 1.计算相差秒数
					* 2.计算分钟数后剩余的秒数
					*/
					var millisesecondsForSec = miliisesecondsForMin%(60*1000);
					var discrepantSecs = Math.floor(millisesecondsForSec/1000);
		
					//alert(" 相差 " + discrepantDays + "天 " + discrepantHours + "小时 " + discrepantMins + " 分钟" + discrepantSecs + " 秒");
					return {
						discrepantDays : discrepantDays,
						discrepantHours : discrepantHours,
						discrepantMins : discrepantMins,
						discrepantSecs : discrepantSecs
					};
				}
				
			},
			// 工具模块
			Tool : {
				// 检测浏览器
				tool_browserdetect : function(){
					
				},
				// 时间格式化---JSON时间格式转换成正常时间格式
				tool_dateformat : function(jsondate){
					var date = new Date(parseInt(jsondate, 10));	
					var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
					var currentDate = date.getDate < 10 ? "0" + date.getDate() : date.getDate();
					var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
					var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
					var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
					
					return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
				},
				// 日期比较大小前的格式化函数(yyyy-MM-dd)
				tool_todatecompare : function(str){
					var sd = str.split("-");
        			return new Date(sd[0],sd[1],sd[2]);
				},
				// 日期时间比较大小前的格式化函数(yyyy-MM-dd hh:mm:ss)
				tool_todatetimecompare : function(startDate, endDate){
					var compareResult = false;
					if(startDate.length>0 && endDate.length>0){
						var startDateTemp = startDate.split(" ");
						var endDateTemp = endDate.split(" ");
						
						var arrStartDate = startDateTemp[0].split("-");
						var arrEndDate = endDateTemp[0].split("-");
						
						var arrStartTime = startDateTemp[1].split(":");
						var arrEndTime = endDateTemp[1].split(":");
						
						var allStartDate = new Date(arrStartDate[0],arrStartDate[1],arrStartDate[2],arrStartTime[0],arrStartTime[1],arrStartTime[2]);
						var allEndDate = new Date(arrEndDate[0],arrEndDate[1],arrEndDate[2],arrEndTime[0],arrEndTime[1],arrEndTime[2]);
						
						if(allStartDate.getTime() >= allEndDate.getTime()){
							compareResult = true;
						}
						//var compareResult = allStartDate.getTime() >= allEndDate.getTime() ? true : false; 
						
					}
					return compareResult;
				}
			}
			
		};
	}
	
	// 获取单例对象接口
	return function(){
		// 如果为创建单例将创建单例
		if(!_instance){
			_instance = new Qbff();
		}
		
		// 返回单例
		return _instance;
	};
	

})();

//通过LazyUtil对象成功获取内部创建的单例对象	LazyUtil().get('COUNT');	LazyUtil().Util.util_exitsystem();

