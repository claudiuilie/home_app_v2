
let incomeBlock = document.getElementById("add_income_block");
let outcomeBlock = document.getElementById("add_outcome_block");
let modalForm = document.getElementById("wallet_create_form");
let modalInputs = modalForm.getElementsByTagName("input");
let modal = document.getElementById("wallet_create_modal");
let incomeLegend = document.getElementById("income_legend");
let outcomeLegend = document.getElementById("outcome_legend");
let totalIncomeInput = document.getElementById("total_income");
let totalOutcomeInput = document.getElementById("total_outcome");
let incomeInput = document.getElementById("income");
let outcomeInput = document.getElementById("outcome");
let flow;
let formData = {"income":{},"outcome":{}, total_income:0,total_outcome:0};

function selectInputType(button){
    flow = button.name;
    console.log(flow);
}

function addValue(input){

    let details = modalInputs[0].value;
    let value = modalInputs[1].value;
    details.trim();

    if(details === "" || value ===""){
        generateAlert(modalForm,"warning" ,"Details and Value required!");
    }else{
        if(typeof formData[`${flow}`][`${details}`] == 'undefined'){

            value = setDigits(value,2);
            details.toLowerCase();

            if(flow === "income"){
                formData.income[`${details}`] = value;
                formData.total_income = calculateWithDigits(formData.total_income + value, 2)
                incomeBlock.appendChild(createInput(details,value,flow));
            }
            else if(flow === "outcome"){
                formData.outcome[`${details}`] = value;
                formData.total_outcome = calculateWithDigits(formData.total_outcome + value, 2);
                outcomeBlock.appendChild(createInput(details,value,flow));
            }
            updateLegend();
            $(modal).modal("hide");
        }else{
            generateAlert(modalForm, "danger" , "Duplicate entry!");
        }
        modalInputs[0].value = '';
        modalInputs[1].value = '';
    }

}

function calculateWithDigits(x,digits){
    return parseFloat(x.toFixed(digits)) ;
}

function setDigits(input , digits){
    let i = parseFloat(input);
    return parseFloat(i.toFixed(2));
}

function removeInput(element){
    let input = document.getElementById(element.getAttribute("name"));
    let inputClassList = input.classList;
    let flow = inputClassList[2];
    let inputValue;
    let inputGroup = element.parentElement.parentElement;

    if(input.value.toString().length === 0)
        inputValue = 0;
    else
        inputValue = setDigits(input.value,2);

    formData[`${flow}_total`] = calculateWithDigits(formData[`${flow}_total`] - inputValue, 2);
    delete formData[flow][input.name];
    inputGroup.remove();
    updateLegend();
}

function updateValues(input){

    let inputValue;
    let inputDetails = input.name;
    let inputClassList = input.classList;
    let flow = inputClassList[2];

    if(input.value.toString().length === 0)
        inputValue = 0;
    else
        inputValue = setDigits(input.value,2);

    let newTotal = calculateWithDigits(formData[`${flow}_total`] - formData[flow][inputDetails], 2);
    formData[`${flow}_total`] = calculateWithDigits(newTotal + inputValue,2);
    formData[flow][inputDetails] = inputValue;
    updateLegend();
}

function updateLegend(){
    incomeLegend.innerHTML = "Income: "+formData.total_income;
    outcomeLegend.innerHTML = "Outcome: -"+formData.total_outcome;
    totalIncomeInput.value = formData.total_income;
    totalOutcomeInput.value = formData.total_outcome;
    incomeInput.value = JSON.stringify(formData.income);
    outcomeInput.value = JSON.stringify(formData.outcome);
}

function createInput(details,value, flowType) {

    let newInputGroup = document.createElement("div");
    newInputGroup.classList.add("input-group","input-group-sm","mb-2");

    let groupPrepend = document.createElement("div");
    groupPrepend.classList.add("input-group-prepend");

    let spanPrepend = document.createElement("span");
    spanPrepend.classList.add("input-group-text","text-info", "text-capitalize");
    spanPrepend.innerHTML = "&nbsp"+details;

    let iconPrepend = document.createElement("i");
    iconPrepend.classList.add("fas","fa-coins");

    let newInput = document.createElement("input");
    newInput.classList.add("form-control","text-center", flowType, "text-info");
    newInput.setAttribute("type","number");
    newInput.setAttribute("id",details+value);
    newInput.setAttribute("placeholder","Enter value..");
    newInput.setAttribute("name",details);
    newInput.setAttribute("onKeyUp","updateValues(this)");
    newInput.attributes.required = "required";
    newInput.value = value;

    let groupAppend = document.createElement("div");
    groupAppend.classList.add("input-group-append");

    let buttonAppend = document.createElement("span");
    buttonAppend.classList.add("btn","btn-danger");
    buttonAppend.setAttribute("onclick", "removeInput(this)");
    buttonAppend.setAttribute("name",details+value)
    buttonAppend.innerHTML = `<i class=\"fas fa-times\"></i>`;

    groupPrepend.appendChild(spanPrepend);
    spanPrepend.prepend(iconPrepend);
    newInputGroup.appendChild(groupPrepend);
    newInputGroup.appendChild(newInput);
    groupAppend.appendChild(buttonAppend);
    newInputGroup.appendChild(groupAppend);

    return newInputGroup;
}

