

function congSPCT(ev) {
	let id = $(ev.target).attr('id');
	fetch('/home/chitietgiohang/themsanpham/' + id).then(response => response.text()).then(data => {
		$('#table-content').html(data);
	});
}
function truSPCT(ev) {
	let id = $(ev.target).attr('id');
	fetch('/home/chitietgiohang/trusanpham/' + id).then(response => response.text()).then(data => {
		$('#table-content').html(data);
	});

}
function xoaSPCT(ev) {
	let id = $(ev.target).attr('id');
	var r = confirm("Xác nhận xóa sản phẩm!");
	if (r == true) {
		fetch('/home/chitietgiohang/xoasanpham/' + id)
			.then(response => response.text())
			.then(data => {
				$('#table-content').html(data);
			});
	} else {
		fetch('/home/chitietgiohang')
			.then(response => response.text())
			.then(data => {
				$('#table-content').html(data);
			});
	}

}