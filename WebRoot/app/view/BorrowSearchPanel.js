//分组函数
 var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
 		groupByText:"以此属性分组",
 	 	showGroupsText:"展示分组",
        groupHeaderTpl: '分组名: {name} ({rows.length} 项)'
    });
/**
 * 借阅查询
 */
Ext.define("AM.view.BorrowSearchPanel",{
	extend:'Ext.grid.Panel',
	//title:'借阅查询',
	alias:'widget.borrowSearchPanel',
	iconCls:"date",
    border:false,
    hidden:true,//默认隐藏
    store:'BorrowSearchStore',
    closable:true,//可以关闭
    autoScroll:true,
    closeAction:"hide",
    features:[groupingFeature],
    tbar:["请输入要查询内容:",
          {xtype:"textfield",id:"borrow-search-text",emptyText:"图书类别,图书名称",width:200},
          {xtype:'button',text:'查询',id:"borrow-search-btn",iconCls:'borrow_search'}, "-",
          {xtype:'button',text:'刷新',id:'borrow-update',iconCls:'arrow_refresh'},"-",
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
       {
		text: "图书ID",
		dataIndex: 'book_id',
		width:100,
		sortable: true
	},{
    	text : "图书类别",
		dataIndex : 'book_type',
		//   xtype:"combobox",
		width : 150,
		sortable : true
	},{
		text: "图书名称",
		dataIndex: 'book_name',
		width:200,
		sortable: true
	},{
		text: "图书isbn",
		dataIndex: 'book_isbn',
		width:200,
		sortable: true
	},{
		text: "所借数量",
		dataIndex: 'book_count',
		width:200,
		sortable: true
	},{
		text: "读者卡号",
		dataIndex: 'user_card',
		width:200,
		sortable: true
	},{
		text: "读者姓名",
		dataIndex: 'user_name',
		width:200,
		sortable: true
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
    	}
	},{
		text: "借出日期",
		dataIndex: 'borrow_date',
		width:200,
		sortable: true,
		xtype:'datecolumn',
		format:"Y年m月d日"
	},{
		text: "应还日期",
		dataIndex: 'return_date',
		width:200,
		xtype:'datecolumn',
		format:"Y年m月d日",
		sortable: true
	},{
		text: "联系电话",
		dataIndex: 'phone_number',
		width:200,
		sortable: true
	}],
	 dockedItems:[	//分页
	            	{xtype:'pagingtoolbar',
	            		store:'BorrowSearchStore',
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