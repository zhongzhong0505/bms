//分组函数
 var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
 		groupByText:"以此属性分组",
 	 	showGroupsText:"展示分组",
        groupHeaderTpl: '分组名: {name} ({rows.length} 项)'
    });

Ext.define("AM.view.UserPanel",{
	extend:'Ext.grid.Panel',
	title:'读者基本信息',
	alias:'widget.userPanel',
	iconCls:"application_xp",
	
    border:false,
    store:'UserStore',
    closable:true,//可以关闭
    hidden:true,
    autoScroll:true,
    closeAction:"hide",
    features:[groupingFeature],
    tbar:[{xtype:'button',text:'添加',id:"user-add",iconCls:'table_add'},"-",
          {xtype:'button',text:'删除',id:"user-del",iconCls:'table_delete'},"-",
      	  {xtype:'button',text:'保存修改',id:"user-save",iconCls:'table_edit'},"-",
      	  {xtype:'button',text:'刷新',id:"user-update",iconCls:'arrow_refresh'},"-",
      	  {xtype:'button',text:'导出数据',id:"user-export",iconCls:'door_out'},"-",
      	  {xtype:'button',text:'导入数据',id:"user-import",iconCls:'door_in'},"-",
      	  {xtype:'button',text:'数据统计',id:"user-data-sta",iconCls:'chart_bar'},"-",
          "请输入要查询内容:",
          {xtype:"textfield",id:"user-search-text",emptyText:"读者卡号,姓名,邮箱,电话号码",width:200},
          {xtype:'button',id:"user-search-btn",text:'查询',iconCls:'borrow_search'},
          "-",/*
          {		xtype:'button',
        		text:'取消分组',
        		iconCls:"link_go",
            	handler : function(){
                	groupingFeature.disable();
            	}
        	  },"-",*/
          {xtype:"button",text:'打印',iconCls:"borrow_printer",handler:function(){window.print();}}
         ],
    columns: [
		//加上序号
		Ext.create("Ext.grid.RowNumberer",{}),
    {
		text: "用户ID",
		dataIndex: 'user_id',
		width:100,
		sortable: true
	},{
    	text : "读者卡号",
		dataIndex : 'user_card',
		width : 150,
		sortable : true/*,
		field:{
    		xtype:'textfield',
    		allowBlank:false
    	}*/
	},{
		text: "读者姓名",
		dataIndex: 'user_name',
		width:200,
		sortable: true,
    	field:{
    		xtype:'textfield',
    		allowBlank:false
    	}
	},{
		text: "读者性别",
		dataIndex: 'user_sex',
		width:100,
		sortable: true,
		renderer:function(value){
    		if(value){
    			if(value == "女"){
    				return "<font color='green'>"+value+"</font>";
    			}else{
    				return "<font color='red'>"+value+"</font>";
    			}
    		}
    	},
    	field:{
			xtype:"combo",
			labelWidth:80,
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
    	}
	},{
		text: "读者邮箱",
		dataIndex: 'user_email',
		width:200,
		sortable: true,
    	field:{
    		xtype:'textfield',
    		allowBlank:false
    	}
	},{
		text: "电话号码",
		dataIndex: 'user_phone',
		width:200,
		sortable: true,
    	field:{
    		xtype:'textfield',
    		allowBlank:false
    	}
	
	},{
		text: "出生日期",
		dataIndex: 'user_date',
		width:200,
		xtype:"datecolumn",
		format:"Y年m月d日",
		sortable: true,
		field:{
			xtype:'datefield',
			allowBlank:false,
			format:"Y年m月d日"
		}
	},{
		text: "卡的状态",
		dataIndex: 'user_status',
		width:100,
		sortable: true,
		field:{
			xtype:"combo",
			labelWidth:80,
			emptyText :"请选择",
			store : new Ext.data.SimpleStore({  
                fields : ['value', 'text'],  
                data : [['V1', '卡挂失'],  
                        ['V2', '正常']
                       ]  
            }),  
            editable : false,  
            triggerAction:'all',  
            valueField : 'text',  
            displayField : 'text' 
    	},
		renderer:function(value){
    		if(value){
    			if(value == "卡挂失"){
    				return "<font color='red'>"+value+"</font>";
    			}else{
    				return "<font color='green'>"+value+"</font>";
    			}
    		}
    	}

	},{
		text: "所在院系",
		dataIndex: 'user_faculty',
		width:200,
		sortable: true,
		field:{
			xtype:"combo",
			labelWidth:80,
			emptyText :"请选择",
			store : new Ext.data.SimpleStore({  
                fields : ['value', 'text'],  
                data : [['V1', '美术学院'],  
                        ['V2', '外国语学院'],
                        ['V3', '软件服务外包学院'],
                        ['V4', '旅游与管理工程学院']                    
                       ]  
            }),  
            editable : false,  
            triggerAction:'all',  
            valueField : 'text',  
            displayField : 'text' 
    	}
	}],
	 dockedItems:[	//分页
	            	{xtype:'pagingtoolbar',
	            		store:'UserStore',
	            		dock:'bottom',
	            		displayInfo:true
	        }],
	plugins:[	//插件
    	Ext.create("Ext.grid.plugin.CellEditing",{
    	
    		clicksToEdit:2//点击多少下显示编辑框
    	})
    ],
    
    selType:'checkboxmodel',//选择框
    
    multiSelect:true,	//允许多选
    initComponent:function(){
    	this.callParent(arguments);
    }     
});