function generateAlert(container, type ,message){

    let alert = document.createElement("div");
    alert.setAttribute("role","alert");
    alert.classList.add("alert");
    alert.classList.add(`alert-${type}`);
    alert.classList.add("alert-dismissible");
    alert.classList.add("fade");
    alert.classList.add("show");
    alert.classList.add("m-3");
    alert.classList.add("p-1");
    alert.innerHTML = message;
    let alertButton = document.createElement("button");
    alertButton.classList.add("close")
    alertButton.setAttribute("type","button");
    alertButton.setAttribute("data-dismiss","alert");
    alertButton.setAttribute("aria-label","Close");
    let alertSpan = document.createElement("span");
    alertSpan.setAttribute("aria-hidden","true");
    alertSpan.innerHTML = "&times;"
    alertButton.appendChild(alertSpan);
    alert.appendChild(alertButton);
    container.prepend(alert);

    setTimeout(()=>{
        let modalAlert = document.getElementsByClassName("alert");
        for(let k = 0; k< modalAlert.length; k++)
            modalAlert[k].remove();
    },3000);
}