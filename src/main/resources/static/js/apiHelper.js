let OptionGet = {
    type: "GET",
    contentType: "application/json",
    url: "",
    dataType: 'json',
    cache : false,
    timeout: 600000,
    summary:"any",
    acceptance:"any",
    status:"any",
}

function apiGetData(url){
    OptionGet.url = url;
    return new Promise((resolve,reject)=>{
        $.ajax(OptionGet).done(data=>{
            resolve(data);
        }).fail(err=>{
            reject(err);
        });
    });
}