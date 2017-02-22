
//分组函数
 var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
 		groupByText:"以此属性分组",
 	 	showGroupsText:"展示分组",
        groupHeaderTpl: '分组名: {name} ({rows.length} 项)'
    });

Ext.define("AM.view.MemoPanel",{
	extend:'Ext.grid.Panel',
	title:'备忘管理',
	alias:'widget.memoPanel',
	iconCls:"book_open",
    border:false,
    store:'MemoStore',
    hidden:true,//默认隐藏
    closable:true,//可以关闭
    autoScroll:true,
    closeAction:"hide",
    features:[groupingFeature],
    tbar:[{xtype:'button',text:'添加备忘',id:"memo-add",iconCls:'table_add'},"-",
          {xtype:'button',text:'删除',id:"memo-del",iconCls:'table_delete'},"-",
      	  {xtype:'button',text:'保存修改',id:"memo-save",iconCls:'table_edit'},"-",
      	  {xtype:'button',text:'刷新',id:"memo-update",iconCls:'arrow_refresh'},"-",
          "请输入要查询内容:",
          {xtype:"textfield",id:"memo-search-text",emptyText:"备忘内容,状态",width:200},
          {xtype:'button',id:"memo-search-btn",text:'查询',iconCls:'borrow_search'},
          "-",/*
          {
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
		text: "备忘编号",
		dataIndex: 'memo_id',
		width:100,
		sortable: true
	},{
    	text : "当前状态",
		dataIndex : 'now_status',
		width : 150,
		sortable : true,
		field:{
			xtype:"combo",
			labelWidth:80,
			emptyText :"请选择",
			store : new Ext.data.SimpleStore({  
                fields : ['value', 'text'],  
                data : [['V1', '完成'],
                        ['V2', '进行中'], 
                        ['V3', '未完成']
                       ]  
            }),  
            editable : false,  
            triggerAction:'all',  
            valueField : 'text',  
            displayField : 'text' 
    	},
    	renderer:function(value){
    		if(value){
    			if(value == "完成"){
    				return "<font color='blue'>"+value+"</font>";
    			}else if(value == "未完成"){
    				return "<font color='red'>"+value+"</font>";
    			}else{
    				return "<font color='green'>"+value+"</font>";
    			}
    		}
    	}
	},{
		text: "备忘日期",
		dataIndex: 'memo_date',
		width:200,
    	xtype:"datecolumn",
		format:"Y年m月d日",
		field:{
			xtype:'datefield',
    		allowBlank:false,
    		format:"Y年m月d日"
		},
		sortable: true
	},{
		text: "备忘内容",
		dataIndex: 'memo_info',
		width:200,
		sortable: true,
    	field:{
    		xtype:'textfield',
    		allowBlank:false
    	}
	},{
		text: "是否提示",
		dataIndex: 'isTip',
    	field:{
			xtype:"combo",
			labelWidth:80,
			emptyText :"请选择",
			store : new Ext.data.SimpleStore({  
                fields : ['value', 'text'],  
                data : [['V1', '是'],  
                        ['V2', '否']
                       ]  
            }),  
            editable : false,  
            triggerAction:'all',  
            valueField : 'text',  
            displayField : 'text' 
    	},
		width:100,
		sortable: true
    	
	},{
		text: "提示类型",
		dataIndex: 'tip_type',
		width:200,
		sortable: true,
		field:{
			xtype:"combo",
			labelWidth:80,
			emptyText :"请选择",
			store : new Ext.data.SimpleStore({  
                fields : ['value', 'text'],  
                data : [['V1', '邮件'],  
                        ['V2', '电话']
                       ]  
            }),  
            editable : false,  
            triggerAction:'all',  
            valueField : 'text',  
            displayField : 'text' 
		}
	},{
		text: "是否涉及读者",
		dataIndex: 'of_user',
		width:200,
		sortable: true,
		field:{
			xtype:"combo",
			labelWidth:80,
			emptyText :"请选择",
			store : new Ext.data.SimpleStore({  
                fields : ['value', 'text'],  
                data : [['V1', '是'],  
                        ['V2', '否']
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
	            		store:'MemoStore',
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