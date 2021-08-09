/**
 * @param {String} id
 * @param {Boolean} isShow
 * @param {String} className 
 */
function showPopup(id,isShow,className){
    let popup = document.getElementById(id);
    popup.classList.toggle(className,isShow);
}

async function clickViewDetail(id){

    try {
        let data = await apiGetData("http://localhost:8080/api/order-detail?id="+id);

        showOrderEdtail(data);

    } catch (error) {
        alert("Lỗi khi load data");
        console.log(error);
    }


}

function showOrderEdtail(data){
    console.log(data);

    showPopup("popup-detail",true,"popup-show");

    let idOrder = document.getElementById("detail-id");

    

    /// total
    let total = document.getElementById("order-detail-total");
    total.innerHTML = "Tổng tiền: " + data.total; 

    // items
    let orderItems = document.getElementById("order-items");
    orderItems.innerHTML = "";
    data.items.forEach(item => {

        let itemDOM = `<tr class="tt-ls-ct" style="font-size: 18px">
                        <td class="title-ctls-mad"> ${item.id_product}</td>
                        <td class="title-ctls-anhsp"><img alt="" src="${item.image}" width="10" /></td>
                        <td class="title-ctls-tenkhd">${item.name}</td>
                        <td class="title-ctls-ngmuad">${item.price} đ</td>
                        <td class="title-ctls-sdtd">${item.amount} chiếc </td>
                        <td class="title-ctls-diachid">${item.total} đ</td>
                    </tr>`;

        orderItems.innerHTML+= itemDOM;
    });
}