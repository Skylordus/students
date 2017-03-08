document.getElementById("clients-menu").addEventListener("click", function() {show(2);},false);
document.getElementById("drivers-menu").addEventListener("click", function() {show(3);},false);
document.getElementById("admins-menu").addEventListener("click", function() {show(4);},false);
document.getElementById("orders-menu").addEventListener("click", function() {show(1);},false);

show(1);

function show(selected_table) {

    switch (selected_table) {
        case 1:
            document.getElementById("orders-menu").style.background = "rgba(255,255,255,0.4)";
            document.getElementById("clients-menu").style.background = "inherit";
            document.getElementById("drivers-menu").style.background = "inherit";
            document.getElementById("admins-menu").style.background = "inherit";
            document.getElementById("table-orders").style.display = "block";
            document.getElementById("table-clients").style.display = "none";
            document.getElementById("table-drivers").style.display = "none";
            document.getElementById("table-admins").style.display = "none";
            break;
        case 2:
            document.getElementById("clients-menu").style.background = "rgba(255,255,255,0.4)";
            document.getElementById("drivers-menu").style.background = "inherit";
            document.getElementById("admins-menu").style.background = "inherit";
            document.getElementById("orders-menu").style.background = "inherit";
            document.getElementById("table-orders").style.display = "none";
            document.getElementById("table-clients").style.display = "block";
            document.getElementById("table-drivers").style.display = "none";
            document.getElementById("table-admins").style.display = "none";
            break;
        case 3:
            document.getElementById("drivers-menu").style.background = "rgba(255,255,255,0.4)";
            document.getElementById("admins-menu").style.background = "inherit";
            document.getElementById("orders-menu").style.background = "inherit";
            document.getElementById("clients-menu").style.background = "inherit";
            document.getElementById("table-orders").style.display = "none";
            document.getElementById("table-clients").style.display = "none";
            document.getElementById("table-drivers").style.display = "block";
            document.getElementById("table-admins").style.display = "none";
            break;
        case 4:
            document.getElementById("admins-menu").style.background = "rgba(255,255,255,0.4)";
            document.getElementById("clients-menu").style.background = "inherit";
            document.getElementById("drivers-menu").style.background = "inherit";
            document.getElementById("orders-menu").style.background = "inherit";
            document.getElementById("table-orders").style.display = "none";
            document.getElementById("table-clients").style.display = "none";
            document.getElementById("table-drivers").style.display = "none";
            document.getElementById("table-admins").style.display = "block";
            break;
        default:
    }
}

function createEditForm(fid,lid) {
    var x = document.getElementsByClassName(fid);
    var y = document.getElementsByClassName(lid);
    var i;
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }    
    for (i = 0; i < y.length; i++) {
        y[i].style.display = "block";
    }
}

function submitForm(form_id,inputs) {
    var form = document.getElementById(form_id);
    //console.log(form);
    var x = document.getElementsByClassName(inputs);
    for (var i = 0; i < x.length; i++) {
        form.appendChild(x[i]);
    }
    //console.log(form);
    form.submit();
}