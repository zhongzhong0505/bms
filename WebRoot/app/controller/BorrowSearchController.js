
//为结点增加右键菜单
var menu1 = new Ext.menu.Menu({

	items : [  {
		text : "归还操作",
		iconCls : "book_previous",
		handler:function(){
			//得到表格对象
			var grid = Ext.getCmp("borrow-search-panel");
			
			//得到选中的行
			var select = grid.getSelectionModel().getSelection();
			//得到参数集合
			var result = select[0].data;
			
			Ext.Ajax.request({
					url:'borrow!back',
					params:{
						book_type:result.book_type,
						book_name : result.book_name,
						book_isbn : result.book_isbn,
						user_card : result.user_card,
						user_name : result.user_name,
						user_sex : result.user_sex,
						borrow_date : result.borrow_date,
						phone_number : result.phone_number
					},
					timeout:2000,
					success:function(response,opts){
						Ext.Msg.alert("系统提示","操作成功！");
						grid.getStore().load();
					},
					failure:function(){
					}
			});		
		}
	},{
		text : "查看图书详细信息",
		iconCls : "book_open"
	} ]
});
/**
 * 借阅查询的控制器
 */
Ext.define("AM.controller.BorrowSearchController",{
	
	extend:'Ext.app.Controller',
	init:function(){
		this.control({
			
		//刷新操作=================================
		"borrowSearchPanel button[id=borrow-update]":{
			click:function(buttonObj){
				//得到表格对象
				var grid = buttonObj.ownerCt.ownerCt;
				//得到数据集
				var store = grid.getStore();
			
				store.load();
			}
		},
		"borrowSearchPanel textfield[id=borrow-search-text]":{
			change:function(obj){
				//得到表格对象
				var grid = obj.ownerCt.ownerCt;
				//得到数据集
				var store = grid.getStore();
				//如果搜索框中的内容为空
				if(obj.getValue()==""){
					//将store的url还原
					store.getProxy().url = "borrowSearch";
					//重新加载数据
					store.load();
				}
			}
		},
		//为表格添加右键菜单
		"borrowSearchPanel":{
				
				
			itemcontextmenu:function(view, record, htmlitem, rowIndex, e){
				
				rowNum = rowIndex;
				e.stopEvent();// 停止原有的右键事件
				menu1.showAt(e.getXY());
			},
		
			itemmouseenter:function(){
				menu1.hide();
			}
		},
		"borrowSearchPanel button[id=borrow-search-btn]":{
			click:function(btn){
				//得到表格对象
				var grid = btn.ownerCt.ownerCt;
				//得到数据集
				var store = grid.getStore();
				//console.log(Ext.getCmp("borrow-search-text"));
				
				//得到用户输入的搜索内容
				var text = Ext.getCmp("borrow-search-text").getValue();
				if(text==""){
					Ext.Msg.alert("系统提示","请输入您要要查找的内容!");
				}else{
					//修改store的url
					store.getProxy().url = "borrowSearch!search?keyword="+text;
					//重新加载数据
					store.load();
				}
				
			}
		}
		});
	},
	views:[	
		"BorrowSearchPanel"
		
	],
	stores:[
		"BorrowSearchStore"
		
	],
	models:[
		"BorrowSearchModel"
		
	]
	
});