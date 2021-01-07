function showOrderPositions(id){

    var orderId="order-id-"+id;
    var order=document.getElementById(orderId);
    for(var i=0; i<order.childNodes.length; i++){
        if(order.childNodes[i].className=="positions-table"){
            order.childNodes[i].style.display="table";
            order.setAttribute('onclick','hideOrderPositions('+id+');');
        }
    }
}

function hideOrderPositions(id){
    var orderId="order-id-"+id;
    var order=document.getElementById(orderId);
    for(var i=0; i<order.childNodes.length; i++){
        if(order.childNodes[i].className=="positions-table"){
            order.childNodes[i].style.display="none";
            order.setAttribute('onclick','showOrderPositions('+id+');');
        }
    }
}