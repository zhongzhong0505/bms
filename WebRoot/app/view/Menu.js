
//修改密码

var form = new Ext.form.Panel({
		frame:true,
		border:false,
		bodyStyle:{
			padding:"0 0 0 10"
		},
		items:[{
			xtype:"textfield",
			fieldLabel:"旧  密  码",
			labelWidth:60,
			padding:"10 0 0 0",
			allowBlank: false,
			inputType:'password',
			name:"now_password"
		},{
			xtype:"textfield",
			fieldLabel:"新  密  码",
			labelWidth:60,
			padding:"10 0 0 0",
			allowBlank: false,
			inputType:'password',
			name:"new_password"
		},{
			xtype:"textfield",
			fieldLabel:"确认密码",
			labelWidth:60,
			padding:"10 0 0 0",
			allowBlank: false,
			inputType:'password',
			name:"re_new_password"
		}],
		buttonAlign:"center",
		buttons:[{
			text:"确认修改",
			handler:function(btn){
				var basic = btn.ownerCt.ownerCt.getForm();
				var new_password = basic.findField("new_password").value;
				var re_new_password = basic.findField("re_new_password").value;
				if(new_password != re_new_password){
					Ext.Msg.show({
						title : '错误提示',
						msg : '两次输入的新密码不一致!',
						icon : Ext.Msg.ERROR,
						buttons : Ext.Msg.OK
					});
				}else{
					basic.submit({
						url:"UpdatePasswordServlet",
						success:function(){
							Ext.Msg.show({
								title : '成功提示',
								msg : '修改密码成功!',
								iconCls : "ko",
								buttons : Ext.Msg.OK
							});
						},
						failure:function(basic,res){
							Ext.Msg.show({
								title : '错误提示',
								msg : res.result.data,
								icon : Ext.Msg.ERROR,
								buttons : Ext.Msg.OK
							});
						}
					});
				}
				
			}
		},{
			text:"取消",
			handler:function(btn){
				btn.ownerCt.ownerCt.ownerCt.hide();
			}
		}]
	});



var info = new Ext.form.Panel({
	frame:true,
	randerTo:Ext.getBody(),
	layout:{
		type:"table",
		columns:2
	},
	defaults:{
		
		autoFitErrors: false//输入错误的时候不收缩输入框
	},
	items:[{
		xtype:"box",
		width: 100, //图片宽度   
	    height: 150, //图片高度   
	    id:"imagebox",
	    name:"image",
	    autoShow :true,
	    rowspan: 4,
	    //================
	    autoEl: {   
	        tag: 'img',    //指定为img标签   
	        src: '',    //指定url路径   
	        style: 'cursor:pointer;'
	    }   
	},{
		fieldLabel:"用 户 名",
		disabled:true,
		name:"adminName",
		xtype:"textfield",
		padding:"0 0 0 30",
		labelWidth:60
	},{
		fieldLabel:"真实姓名",
		name:"realName",
		xtype:"textfield",
		padding:"0 0 0 30",
		allowBlank:false,//不能为空
		labelWidth:60
	},{
		fieldLabel:"邮箱",
		name:"email",
		xtype:"textfield",
		padding:"0 0 0 30",
		allowBlank:false,//不能为空
		labelWidth:60
	},{
		fieldLabel:"照片",
		name:"img",
		xtype:"filefield",
		padding:"0 0 0 30",
		labelWidth:60,
		buttonText:"浏览..."
	},{
		xtype:"textfield",
		hidden:true,
		name:"photo"
	}],
	buttonAlign:"center",
	buttons:[{
		text:"上传照片",
		handler:function(btn){
			var basic = btn.ownerCt.ownerCt.getForm();
			var realName = basic.findField("realName").value;
			var email = basic.findField("email").value;
			var name = basic.findField("img").value;
			if(name==undefined){
				Ext.Msg.show({
					title : '警告',
					msg : '请选择要上传的照片!',
					icon : Ext.Msg.WARNING,
					buttons : Ext.Msg.OK
				});
			}else{
				var ret = Ext.Msg.wait("照片上传中...");
				setTimeout(function(){
					ret.hide();
					basic.submit({
						clientValidation: true,
						url:"upload",
						params:{
							realName:realName,
							email:email
						},
						method:"post",
						success:function(basic,response){
						Ext.getCmp("imagebox").getEl().dom.src = response.result.data; 
						}
					});
				},3000);
			}
			
		}
	},{
		text:"保存修改",
		handler:function(btn){
			var basic = btn.ownerCt.ownerCt.getForm();
			var realName = basic.findField("realName").value;
			var email = basic.findField("email").value;
			if(email!="" && realName!=""){
				Ext.Ajax.request({
					url:"SaveUpdateServlet",
					params:{
						realName:realName,
						email:email
					},
					success:function(){
						Ext.getCmp("admin-info").hide();
						Ext.Msg.show({
							title : '成功提示',
							msg : '<img src="images/ok.png">修改信息成功!',
							//iconCls : "ko",
							buttons : Ext.Msg.OK
						});
					}
				});
			}
			
		}
	},{
		text:"取消",
		handler:function(btn){
			btn.ownerCt.ownerCt.ownerCt.hide();
			/*Ext.Ajax.request({
				url:"CancelUpdateServlet"
			});*/
		}
	}]
});


Ext.define("AM.view.Menu",{

	extend:'Ext.panel.Panel',
	alias:'widget.menuPanel',
	title : "系统导航菜单",
	collapsible : true,
	split:true,
	autoScroll:true,
	width:180,
	frame:true,
	height:480,
	padding:"3 5 40 7",
	
	//region : 'north',
	border:false,
	layout:"accordion",
	items:[{
				xtype:"dailyPanel",
				id:"daily-panel"
			},{
				xtype:"readerPanel"
			},{
				xtype:"bookPanel"
			},{
				xtype:"statisticPanel"
			}],
	bbar:[{
		text:"开始",
		iconCls:"plugin",
		menu : new Ext.menu.Menu({
			items : [{
				text : '个人信息',
				iconCls : 'user_go',
				handler:function(){
					if(Ext.getCmp("admin-info")==null){
						new Ext.Window({
							layout:"fit",
							id:"admin-info",
							width:350,
							height:270,
							closeAction : 'hide',
							resizable : false,
							modal : true,
							title : '个人信息',
							items:[info]
						}).show();
					}else{
						Ext.getCmp("admin-info").show();
					}
					
					var adminName = info.getForm().findField("adminName").value;
					if(adminName==undefined){
					
						Ext.Ajax.request({
							url:"admin!info",
							success:function(response){
								var text = response.responseText;
						        //解析json数据
							    var array =  new Ext.JSON.decode(text);
							    //设置表单的值
							    info.getForm().setValues(array.data);
							    
							    Ext.getCmp("imagebox").getEl().dom.src = "upload/"+info.getForm().findField("photo").value;
							}
						});
						
					}
						
				}
			},{
				text : '修改密码',
				iconCls : 'table_edit',
				handler:function(){
					new Ext.Window({
						layout:"fit",
						width:260,
						height:200,
						closeAction : 'close',
						resizable : false,
						modal : true,
						title : '修改密码',
						items:[form]
					}).show();
				}
			},{
				text : '关于系统',
				iconCls : 'icon-info',
				handler : function() {
					new Ext.Window({
						closeAction : 'close',
						resizable : false,
						bodyStyle : 'padding: 7',
						modal : true,
						title : '关于本系统',
						html : '本系统采用目前较为流行的技术实现,前台使用了ExtJs技术' +
								'本程序在火狐浏览器中测试通过!<br><br>主要技术: servlet + jsp + ExtJs4.0<br><br>'
								+ '数&nbsp;&nbsp;据&nbsp;&nbsp;库: Mysql',
						width : 300,
						height : 200
					}).show();
				}
			}, {
				text : '退出系统',
				iconCls : 'icon-delete',
				handler : function() {
					Ext.Msg.confirm('操作提示', '您确定要退出本系统?', function(btn) {
						if ('yes' == btn) {
							Ext.Ajax.request({
								url : 'exit',
								success : function() {
									alert("*****");
									console.info(history);
									location = 'login.jsp';
								},
								failure : function() {
									Ext.Msg.show({
										title : '错误提示',
										msg : '退出系统失败!',
										icon : Ext.Msg.ERROR,
										buttons : Ext.Msg.OK
									});
								}
							});
						}
					});
				}
			}]
		})
	
	}],
    initComponent:function(){
    	this.callParent(arguments);
    }
});