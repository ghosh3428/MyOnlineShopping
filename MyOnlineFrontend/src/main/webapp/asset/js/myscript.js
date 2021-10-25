$(function() {

	var $table = $('#productTable');

	if ($table.length) {

		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		$table.DataTable({
			lengthMenu : [ [ 3, 5, 10, -1 ],
					[ '3 Items', '5 Items', '10 Items', 'ALL' ] ],
			pageLength : 5,
			ajax : {
				url : jsonUrl,
				dataSrc : ''
			},
			columns : [ 
			            {
			            	data : 'code',
			            	bSortable : false,
			            	mRender : function(data, type, row) {

								return '<img src="' + window.contextRoot
										+ '/rs/images/' + data
										+ '.jpg" class="dataTableImg"/>';

							}
			
			            }, {
				data : 'name'
			}, {
				data : 'brand'
			}, {
				data : 'unitPrice',
				mRender : function(data, type, row) {
					return '&#8377; ' + data
				}

			}, {
				data : 'quantity'
			},
			{
				data:'id',
				bSortable : false,
				mRender : function(data, type, row) {

					var str = '';
					str += '<a href="'
						+ window.contextRoot
						+ '/product/show/'
						+ data
						+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

					str += '<a href="#" class="btn btn-warning"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
					

					return str;

				}
			}

			]
		});
	}
});
