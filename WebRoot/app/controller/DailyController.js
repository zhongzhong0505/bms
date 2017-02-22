//图书类型的数据集合
var store = new Ext.data.SimpleStore({  
            fields : ['value', 'text'],  
            data : [['V1', '政治法律'],  
                    ['V2', '哲学宗教'],
                    ['V3', '经济'],
                    ['V4', '文学'],
                    ['V5', '历史'],
                    ['V6', '小说']
                   ]  
}); 

//借阅登记的panel
var formpanel = new Ext.form.Panel({
	frame:true,
	//id:"borrow-register-panel",
    layout: {
        type: 'table',
        columns: 2
    },
	 items:[{
		// xtype:"textfield",
		 fieldLabel:"图书类别",
		 xtype:"combo",
		 store:store,
		 emptyText :"请选择",
         editable : false,  
         triggerAction:'all',  
         valueField : 'text',  
         displayField : 'text' ,
		 padding:"0 0 0 30",
         name: 'book_type',
         labelWidth:60,
         allowBlank: false
	 },{
		 xtype:"textfield",
		 fieldLabel:"图书名称",
		 padding:"0 0 0 30",
         name: 'book_name',
         labelWidth:60,
         allowBlank: false
	 },{
		 xtype:"textfield",
		 fieldLabel:"图书isbn",
		 padding:"0 0 0 30",
		 labelWidth:60,
         name: 'book_isbn',
         allowBlank: false
	 },{
		 xtype:"textfield",
		 fieldLabel:"读者卡号",
		 padding:"0 0 0 30",
		 labelWidth:60,
         name: 'user_card',
         allowBlank: false
	 },{
		 xtype:"textfield",
		 fieldLabel:"读者姓名",
		 padding:"0 0 0 30",
		 labelWidth:60,
         name: 'user_name',
         allowBlank: false
	 },{
		 allowBlank: false,
		 fieldLabel:"读者性别",
		 xtype:"combo",
		 padding:"0 0 0 30",
		 labelWidth:60,
		 name:"user_sex",
		 emptyText :"请选择",
		 store : new Ext.data.SimpleStore({  
            fields : ['value', 'text'],  
            data : [['V1', '男'],  
                    ['V2', '女'] 
                   ]  
         }),  
         editable : false,  
         triggerAction:'all',  
         valueField : 'text',  
         displayField : 'text' 
	 },{
		 xtype:"datefield",
		 fieldLabel:"借出日期",
		// xtype:"datecolumn",
		 format:"Y年m月d日",
		 padding:"0 0 0 30",
		 labelWidth:60,
         name: 'borrow_date',
         allowBlank: false
	 },{
		 xtype:"datefield",
		 fieldLabel:"应还日期",
		 format:"Y年m月d日",
		 padding:"0 0 0 30",
		 labelWidth:60,
         name: 'return_date',
         allowBlank: false
	 },{
		 xtype:"textfield",
		 fieldLabel:"联系电话",
		 padding:"0 0 0 30",
		 labelWidth:60,
         name: 'phone_number',
         allowBlank: false
	 },{
	 	xtype:"textfield",
		 fieldLabel:"借书数量",
		 padding:"0 0 0 30",
		 labelWidth:60,
         name: 'book_count',
         allowBlank: false
	 }],
	 buttonAlign:"center",
	 buttons:[{
	 		xtype:"button",
	 		text:"验证卡号",
	 		handler:function(btn){
	 			var temp = btn.ownerCt.ownerCt;
				var card = temp.getComponent(3).getValue();
				Ext.Ajax.request({
				    url: 'borrow!valid',
				    params: {
				        user_card: card
				    },
				    success:function(response){
				    	
				    	var temp = Ext.JSON.decode(response.responseText);
				    	
				    	if(temp.success == "true"){
				    		btn.ownerCt.ownerCt.getComponent(4).setValue(temp.data.user_name);
					    	btn.ownerCt.ownerCt.getComponent(5).setValue(temp.data.user_sex);
					    	btn.ownerCt.ownerCt.getComponent(6).setValue(new Date());
					    	btn.ownerCt.ownerCt.getComponent(8).setValue(temp.data.user_phone);
					    	//如果卡号存在，则激活确认按钮
				    		//Ext.getCmp("book-submit-button").setDisabled(false);
				    	}else{
				    		Ext.Msg.alert("系统提示","卡号不存在！");
				    	}
				    	
				    },
				    failure:function(){
				    	
				    }
				});
	 		}
	 },{
		 xtype:"button",
		 text:"确认",
		 id:"book-submit-button",
		 handler:function(buttonObj){
			 var basic = buttonObj.ownerCt.ownerCt.getForm();
			 basic.submit({
			 	waitTitle: '提示信息',
	            waitMsg: '正在处理...',
	    		url:'borrow',//发送请求的url
	    		success: function(response,data){
	    			console.info(data);
			    	Ext.Msg.alert("系统提示",data.result.msg);
			    	buttonObj.ownerCt.ownerCt.ownerCt.hide();
			    	Ext.getCmp("book-info-panel").getStore().load();
			    },
			    failure:function(response,data){
			    	console.info(data);
			    	Ext.Msg.alert("系统提示",data.result.msg);
			    }
			 });
			
		 }
	 },{
		 xtype:"button",
		 text:"取消" ,
		 handler:function(buttonObj){
				buttonObj.ownerCt.ownerCt.ownerCt.hide();
			}
	 }]
});

var bwin = new Ext.window.Window({
	title:"借阅登记",
	width:450,
	id:"borrow-register-win",
	closeAction:"hide",
	modal:true,
	height:300,
	resizable:false,
	resizable:false,
	layout:"fit",
	items:[formpanel]
});
//还书登记的panel
var panel = new Ext.form.Panel({
	frame:true,
	padding:"5 0 0 0",
	layout: {
        type: 'table',
        columns: 2
    },
	items:[{
		fieldLabel:"图书类型",
		xtype:"combo",
		store:store,
		emptyText :"请选择",
        editable : false,  
        triggerAction:'all',  
        valueField : 'value',  
        displayField : 'text' ,
		padding:"0 0 0 20",
        name: 'book_type',
        labelWidth:60,
        allowBlank: false
	},{
		xtype:"textfield",
		fieldLabel:"图书名称",
		padding:"0 0 0 20",
		labelWidth:60,
        name: 'book_name',
        allowBlank: false
	},{
		xtype:"textfield",
		fieldLabel:"图书isbn",
		padding:"0 0 0 20",
		labelWidth:60,
        name: 'book_isbn',
        allowBlank: false
	},{
		xtype:"textfield",
		fieldLabel:"读者卡号",
		padding:"0 0 0 20",
		labelWidth:60,
        name: 'user_card',
        allowBlank: false
	},{
		xtype:"textfield",
		fieldLabel:"还书数量",
		padding:"0 0 0 20",
		labelWidth:60,
        name: 'book_count',
        allowBlank: false
	},{
		 xtype:"datefield",
		 fieldLabel:"还书日期",
		 format:"Y年m月d日",
		 padding:"0 0 0 20",
		 labelWidth:60,
         name: 'return_date',
         allowBlank: false
	}],
	buttonAlign:"center",
	buttons:[{
		text:"确认",
		handler:function(buttonObj){
			var basic = buttonObj.ownerCt.ownerCt.getForm();
			 basic.submit({
				 	waitTitle: '提示信息',
		            waitMsg: '正在处理...',
		    		url:'borrow!back',//发送请求的url
		    		success: function(){
				    	Ext.Msg.alert("系统提示","<img src='images/ok.png'>操作成功！");
				    	buttonObj.ownerCt.ownerCt.ownerCt.hide();
				    	Ext.getCmp("book-info-panel").getStore().load();
				    },
				    failure:function(){
				    	Ext.Msg.alert("系统提示","您填写的数据有误！");
				    }
			 });
		}
	},{
		text:"取消",
		handler:function(buttonObj){
				buttonObj.ownerCt.ownerCt.ownerCt.hide();
		}
	}]
});
var rwin = new Ext.window.Window({
	title:"还书登记",
	id:"return-register-win",
	width:430,
	resizable:false,
	closeAction:"hide",
	modal:true,
	height:250,
	layout:"fit",
	items:[panel]
});
//日常操作的处理
Ext.define("AM.controller.DailyController",{
	
	extend:'Ext.app.Controller',
	init:function(){
		//alert(Ext.getCmp("daily-panel"));
		this.control({
			"dailyPanel":{
				itemdblclick:function(tree,record,item,index,e,eOpts){
					//得到tappanel
					var tabpanel = Ext.getCmp("content-panel");
					
					if(record.get("id") == "borrow-register"){
					   if(bwin){
						   bwin.show();
					   }
					}else if(record.get("id") == "return-register"){
						if(rwin){
							rwin.show();
						}
					}else{
						var myMask = new Ext.LoadMask(tabpanel, {
							msg:"正在加载数据...",
							frame:true	
						});
						myMask.show();
						setTimeout(function(){
							myMask.hide();
							//设置标题
							tabpanel.setTitle(record.get("text"));
							//设置选中的面板为活动窗口
							tabpanel.setActiveTab(record.get("id")+"-panel");
						},1000);
						
					}
					
				}
			}
		});
		
	},
	views:[	
		"BorrowSearchPanel",
		"DailyPanel",
		"Content",
		"StartPanel"
	
	],
	stores:[
		"BorrowSearchStore",
		"DailyStore"
	],
	models:[
		"BorrowSearchModel"
	]
	
});