$('button.close').click(function() {
	$('div.hienThi-giohangmini').css("display", "none");
});

$('button.mo-gio-hang').click(function() {
	$('div.hienThi-giohangmini').css("display", "block");
	fetch('/home/giohang').then(response => response.text()).then(data => {
		$('#table-content').html(data);
	});

});

function truSP(ev) {
	let id = $(ev.target).attr('id');
	fetch('/home/giohang/trusanpham/' + id).then(response => response.text()).then(data => {
		$('#table-content').html(data);
	});

}

function thongBaoThanhCong(ev) {
	alert("Thêm sản phẩm vào giỏ hàng thành công!");
	fetch('/home')
			.then(response => response.text())
			.then(data => {
				$('#table-content').html(data);
			});
}


function themSP(ev) {
	let id = $(ev.target).attr('id');
	fetch('/home/giohang/themsanpham/' + id).then(response => response.text()).then(data => {
		$('#table-content').html(data);
	});
}

function congSP(ev) {
	let id = $(ev.target).attr('id');
	fetch('/home/giohang/congsanpham/' + id).then(response => response.text()).then(data => {
		$('#table-content').html(data);
	});
}

function xoaSP(ev) {
	let id = $(ev.target).attr('id');
	var r = confirm("Xác nhận xóa sản phẩm!");
	if (r == true) {
		fetch('/home/giohang/xoasanpham/' + id)
			.then(response => response.text())
			.then(data => {
				$('#table-content').html(data);
			});
	} else {
		fetch('/home/giohang')
			.then(response => response.text())
			.then(data => {
				$('#table-content').html(data);
			});
	}

}

function chonHang(ev){
	let id = $(ev.target).attr('id');
	fetch('/home/hangsanpham/' + id).then(response => response.text()).then(data => {
		$('#products').html(data);
	});
}

function chonGiaBan(ev){
	let value = $(ev.target).attr('value');
	if(value == "tangdan"){
		fetch('/home/sapxeptheogia/giatang').then(response => response.text()).then(data => {
			$('#products').html(data);
		});
	} else if(value == "giamdan") {
		fetch('/home/sapxeptheogia/giagiam').then(response => response.text()).then(data => {
			$('#products').html(data);
		});
	} else {
		fetch('/home').then(response => response.text()).then(data => {
			$('#products').html(data);
		});
	}
	
}
