let currentUser;
function newReimbursementSubmit(event) {
    event.preventDefault(); // stop page from refreshing
    console.log('submitted');

    const reimbursement = getReimbursementFromInputs();

    fetch('http://localhost:8080/ReimbursementApi/reimbursements', {
        method: 'POST',
        body: JSON.stringify(reimbursement),
        headers: {
            'content-type': 'application/json'
        }
    })
        .then(res => res.json())
        .then(data => {
            addReimbursementToTable(data);
            console.log(data);
        })
        .catch(err => console.log(err));


}

function addReimbursementToTable(reimbursement) {

    // create the row element
    const row = document.createElement('tr');

    // create all the td elements and append them to the row
    // const idData = document.createElement('td');
    // idData.innerText = reimbursement.reimbId;
    // row.appendChild(idData);
    // employee doesnt need id

    const amountData = document.createElement('td');
    amountData.innerText = reimbursement.reimbAmount;
    row.appendChild(amountData);

    const timeSubData = document.createElement('td');
    timeSubData.innerText = new Date(reimbursement.reimbSubmitted);
    row.appendChild(timeSubData);

    const timeResData = document.createElement('td');
    if (!reimbursement.reimbResolved) {
        timeResData.innerText = 'Not Yet Resolved.'
    } else {
        timeResData.innerText = new Date(reimbursement.reimbResolved);
    }
    row.appendChild(timeResData);

    const reimbDesData = document.createElement('td');
    reimbDesData.innerText = reimbursement.reimbDescription;
    row.appendChild(reimbDesData);

    // const reimbAuthData = document.createElement('td');
    // reimbAuthData.innerText = reimbursement.reimbName;
    // row.appendChild(reimbAuthData);
    // not needed because only displaying things for a current user here

    const reimbResolverData = document.createElement('td');
    reimbResolverData.innerText = reimbursement.reimbResolver;
    row.appendChild(reimbResolverData);

    const reimbStatData = document.createElement('td');
    reimbStatData.innerText = reimbursement.reimbStatus;
    row.appendChild(reimbStatData);

    const reimbTypeData = document.createElement('td');
    reimbTypeData.innerText = reimbursement.reimbType;
    row.appendChild(reimbTypeData);

    // append the row into the table
    document.getElementById('reimbursement-table-body').appendChild(row);
}

function getReimbursementFromInputs() {
    const amount = document.getElementById('reimbursement-amount-input').value;
    const description = document.getElementById('reimbursement-description-input').value;
    const type = document.getElementById('reimbursement-type-select').value;
    let typeId;
    if (type === 'Lodging') {
        typeId = 1
    } else if (type === 'Travel') {
        typeId = 2
    } else if (type === 'Food') {
        typeId = 3
    } else {
        typeId = 4
    }

    const reimbursement = {
        reimbAmount: amount,
        reimbDescription: description,
        reimbTypeId: typeId
    }
    return reimbursement;
}

function refreshTable() {
    fetch(`http://localhost:8080/ReimbursementApi/reimbursements?user=${currentUser.ersUsersId}`, { //
        credentials: 'include'
    })
        .then(res => res.json())
        .then(data => {
            data.forEach(addReimbursementToTable)
        })
        .catch(console.log);
}


function getCurrentUserInfo() {
    fetch('http://localhost:8080/ReimbursementApi/auth/session-user', {
        credentials: 'include'
    })
        .then(resp => resp.json())
        .then(data => {
            document.getElementById('users-name').innerText = data.ersUsername
            currentUser = data;
            console.log(currentUser)
            refreshTable();

        })
        .catch(err => {
            console.log(err)
            //window.location = '/login.html'
        })
}

getCurrentUserInfo();