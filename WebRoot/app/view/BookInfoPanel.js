
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

//分组函数
 var groupingFeature = Ext.create('Ext.grid.feature.Grouping',{
 		groupByText:"以此属性分组",
 	 	showGroupsText:"展示分组",
        groupHeaderTpl: '分组名: {name} ({rows.length} 项)'
    });

/**
 * 图书信息
 */
Ext.define("AM.view.BookInfoPanel",{
	extend:'Ext.grid.Panel',
	alias:'widget.bookInfoPanel',
	iconCls:"date",
    border:false,
    hidden:true,//默认隐藏
    store:'BookInfoStore',
    closable:true,//可以关闭
    autoScroll:true,
    closeAction:"hide",
    features:[groupingFeature],
    tbar:[{xtype:'button',text:'添加图书',id:"book-add",iconCls:'table_add'},"-",
          {xtype:'button',text:'删除',id:"book-del",iconCls:'table_delete'},"-",
      	  {xtype:'button',text:'保存修改',id:"book-save",iconCls:'table_edit'},"-",
      	  {xtype:'button',text:'刷新',id:"book-update",iconCls:'arrow_refresh'},"-",
      	  {xtype:'button',text:'导出数据',id:"book-export",iconCls:'door_out'},"-",
      	  {xtype:'button',text:'导入数据',id:"book-import",iconCls:'door_in'},"-",
      	  {xtype:'button',text:'数据统计',id:"book-data-sta",iconCls:'chart_bar'},"-",
          "请输入要查询内容:",
          {xtype:"textfield",id:"book-search-text",emptyText:"书名,出版社,类别",width:200},
          {xtype:'button',id:"book-search-btn",text:'查询',iconCls:'borrow_search'}, "-",
          /*{
      		text:'取消分组',
         	iconCls:"link_go",
          	handler : function(){
              	groupingFeature.disable();
          	}
      	  },"-",*/
      	  {xtype:"button",text:'打印',iconCls:"borrow_printer",id:"book-print"
      	  }],
    columns: [
          //加上序号
      	Ext.create("Ext.grid.RowNumberer",{}),
     {
		text: "图书ID",
		dataIndex: 'book_id',
		width:100,
		sortable: true
	},{
    	text : "图书类别",
		dataIndex : 'book_type',
		width : 150,
		sortable : true,
		field:{
			 xtype:"combo",
			 store:store,
			 emptyText :"请选择",
	         editable : false,  
	         triggerAction:'all',  
	         valueField : 'text',  
	         displayField : 'text' ,
	         name: 'book_type',
	         allowBlank: false
    	}
	},{
		text: "图书名称",
		dataIndex: 'book_name',
		width:200,
		sortable: true,
    	field:{
    		xtype:'textfield',
    		allowBlank:false
    	}
	},{
		text: "图书isbn",
		dataIndex: 'book_isbn',
		width:200,
		sortable: true,
    	field:{
    		xtype:'textfield',
    		allowBlank:false
    	}
	},{
		text: "图书作者",
		dataIndex: 'book_author',
		width:100,
		sortable: true,
    	field:{
    		xtype:'textfield',
    		allowBlank:false
    	}
	},{
		text: "库存数量",
		dataIndex: 'book_count',
		width:100,
		sortable: true,
    	field:{
    		xtype:'textfield',
    		allowBlank:false
    	}
	},{
		text: "出版社",
		
		dataIndex: 'book_press',
		width:200,
		sortable: true,
    	field:{
    		xtype:'textfield',
    		allowBlank:false
    	}
	},{
		text: "出版日期",
		dataIndex: 'book_press_date',
		width:200,
		xtype:"datecolumn",
		format:"Y年m月d日",
		field:{
			xtype:'datefield',
    		allowBlank:false,
    		format:"Y年m月d日"
		},
		sortable: true
		
	},/*{
		text: "馆藏数量",
		dataIndex: 'book_count',
		width:200,
		sortable: true,
    	field:{
    		xtype:'textfield',
    		allowBlank:false
    	}
	},*/{
		text: "图书价格",
		dataIndex: 'book_price',
		width:100,
		sortable: true,
    	field:{
    		xtype:'textfield',
    		allowBlank:false
    	}
	},{
		text: "图书位置",
		dataIndex: 'book_location',
		width:200,
		sortable: true,
    	field:{
    		xtype:'textfield',
    		allowBlank:false
    	}
	},{
		text: "备注信息",
		dataIndex: 'book_note',
		width:300,
		sortable: true,
    	field:{
    		xtype:'htmleditor',
    		width:300,
    		height:200,
    		fontFamilies :["宋体","仿宋","黑体","微软雅黑","Courier New","华文行楷"],

    		allowBlank:false
    	}
	}],
	 dockedItems:[	//分页
	            	{xtype:'pagingtoolbar',
	            		store:'BookInfoStore',
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