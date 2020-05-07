
let incomeBlock = document.getElementById("add_income_block");
let outcomeBlock = document.getElementById("add_outcome_block");
let modalForm = document.getElementById("wallet_create_form");
let modalInputs = modalForm.getElementsByTagName("input");
let modal = document.getElementById("wallet_create_modal");
let flow;
let formData = {"income":{},"outcome":{}, income_total:0,outcome_total:0};

function selectInputType(button){
    flow = button.name;
    console.log(flow);
}

function addValue(input){

    let details = modalInputs[0].value;
    let value = modalInputs[1].value;
    details.trim();
    value.trim();

    let newInput = document.createElement("input");
    newInput.classList.add("form-control");
    newInput.classList.add("text-center");
    newInput.setAttribute("type","number");
    newInput.setAttribute("placeholder","Enter value..");
    newInput.setAttribute("name",details);
    newInput.attributes.required = "required";
    newInput.value = value;

    let newInputLabel = document.createElement("label");
    newInputLabel.classList.add("text-info");
    newInputLabel.setAttribute("for",details);
    newInputLabel.innerHTML = details;


    if(details === "" || value ===""){
        generateAlert(modalForm,"warning" ,"Details and Value required!");
    }else{
        if(typeof formData[`${flow}`][`${details}`] == 'undefined'){

            details.toLowerCase()


            if(flow === "income"){
                formData.income[`${details}`] = value;
                incomeBlock.appendChild(newInputLabel);
                incomeBlock.appendChild(newInput);
                for(let x in formData.income)
                    formData.income_total += formData.income[x];
            }
            else if(flow === "outcome"){
                formData.outcome[`${details}`] = value;
                outcomeBlock.appendChild(newInputLabel);
                outcomeBlock.appendChild(newInput);

                for(let z in formData.outcome)
                    formData.outcome_total += formData.outcome[z];
            }

            $(modal).modal("hide");


        }else{
            generateAlert(modalForm, "danger" , "Duplicate entry!");
        }

        modalInputs[0].value = '';
        modalInputs[1].value = '';
    }

}