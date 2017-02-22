//分组函数
 var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
 		groupByText:"以此属性分组",
 	 	showGroupsText:"展示分组",
        groupHeaderTpl: '分组名: {name} ({rows.length} 项)'
    });
/**
 * 借阅查询
 */
Ext.define("AM.view.BorrowHistoryPanel",{
	extend:'Ext.grid.Panel',
	//title:'借阅查询',
	alias:'widget.borrowHistoryPanel',
	iconCls:"date",
    border:false,
    hidden:true,//默认隐藏
    store:'BorrowHistoryStore',
    closable:true,//可以关闭
    autoScroll:true,
    closeAction:"hide",
    features:[groupingFeature],
    tbar:["请输入要查询内容:",
          {xtype:"textfield",id:"history-search-text",emptyText:"图书名称,读者姓名",width:200},
          {xtype:'button',id:"history-search-btn",text:'查询',iconCls:'borrow_search'}, "-",
          {xtype:'button',text:'刷新',id:"history-update",iconCls:'arrow_refresh'},"-",
          /*{
      		text:'取消分组',
         	iconCls:"link_go",
          	handler : function(){
              	groupingFeature.disable();
          	}
      	  },"-",*/
      	  {xtype:"button",text:'打印',iconCls:"borrow_printer",handler:function(){window.print();}
      	  }],
    columns: [
          //加上序号
      	Ext.create("Ext.grid.RowNumberer",{}),
     {
		text: "读者卡号",
		dataIndex: 'user_card',
		width:100,
		sortable: true
	},{
    	text : "读者姓名",
		dataIndex : 'user_name',
		width : 150,
		sortable : true
	},{
		text: "所借书名",
		dataIndex: 'borrow_book_name',
		width:200,
		sortable: true
	},{
		text: "借书日期",
		dataIndex: 'borrow_book_date',
		width:200,
		xtype:"datecolumn",
		format:"Y年m月d日",
		sortable: true
	},{
		text: "还书日期",
		dataIndex: 'return_book_date',
		width:200,
		xtype:"datecolumn",
		format:"Y年m月d日",
		sortable: true
	},{
		text: "读者电话号码",
		dataIndex: 'user_phone',
		width:200,
		sortable: true
	}],
	 dockedItems:[	//分页
	            	{xtype:'pagingtoolbar',
	            		store:'BorrowHistoryStore',
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