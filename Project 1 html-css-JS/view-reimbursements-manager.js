

let currentUser;
function newFilterSubmit(event) {
    event.preventDefault();

    //use this function to direct it to other functions
    const user = document.getElementById('filter-user-input').value
    const status = document.getElementById('filter-status-select').value
    let userNum;
    let statusNum;
    if (status === 'All') {
        statusNum = 0;
    } else if (status === 'Pending') {
        statusNum = 1;
    } else if (status === 'Approved') {
        statusNum = 2;
    } else {
        statusNum = 3;
    }


    if (!+user && user !== '0') { //if input is not a number convert it to the correct id

        fetch(`http://localhost:8080/ReimbursementApi/users?username=${user}`, {
            credentials: 'include'
        })
            .then(resp => resp.json())
            .then(data => {
                userNum = data.ersUsersId
                if (userNum && statusNum) { //user and status
                    let nums = [userNum, statusNum]
                    refreshTableUS(nums);
                } else if (userNum && !statusNum) { //user and !status
                    refreshTableU(userNum);
                } else if (!userNum && statusNum) { //!user and status
                    refreshTableS(statusNum);
                } else { //!user and !status
                    refreshTable();
                }
            })
            .catch(err => {
                console.log(err)
            })
    } else {
        userNum = +user;
        if (!userNum) {
            userNum = -1;
        }

        if (userNum && statusNum) { //user and status
            let nums = [userNum, statusNum]
            refreshTableUS(nums);
        } else if (userNum && !statusNum) { //user and !status
            refreshTableU(userNum);
        } else if (!userNum && statusNum) { //!user and status
            refreshTableS(statusNum);
        } else { //!user and !status
            refreshTable();
        }
    }

}

function addReimbursementToTable(reimbursement) {

    // create the row element
    const row = document.createElement('tr');

    // create all the td elements and append them to the row
    const idData = document.createElement('td');
    idData.innerText = reimbursement.reimbId;
    row.appendChild(idData);

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

    const reimbAuthData = document.createElement('td');
    reimbAuthData.innerText = reimbursement.reimbName;
    row.appendChild(reimbAuthData);

    const reimbAuthIdData = document.createElement('td');
    reimbAuthIdData.innerText = reimbursement.reimbAuthor;
    row.appendChild(reimbAuthIdData);

    const reimbResolverData = document.createElement('td');
    reimbResolverData.innerText = reimbursement.reimbResName;
    row.appendChild(reimbResolverData);

    const reimbStatData = document.createElement('td');
    reimbStatData.innerText = reimbursement.reimbStatus;
    row.appendChild(reimbStatData);

    const reimbTypeData = document.createElement('td');
    reimbTypeData.innerText = reimbursement.reimbType;
    row.appendChild(reimbTypeData);

    if (reimbursement.reimbStatus === 'Pending') {
        const reimbYesButton = document.createElement('button');
        reimbYesButton.innerText = 'Approve';
        reimbYesButton.setAttribute('onclick', 'approve(this.value)')
        reimbYesButton.setAttribute('class', 'btn btn-lg btn-success btn-block')
        reimbYesButton.setAttribute('value', `${reimbursement.reimbId}`)
        row.appendChild(reimbYesButton);

        const reimbNoButton = document.createElement('button');
        reimbNoButton.innerText = 'Deny';
        reimbNoButton.setAttribute('onclick', 'deny(this.value)')
        reimbNoButton.setAttribute('class', 'btn btn-lg btn-danger btn-block')
        reimbNoButton.setAttribute('value', `${reimbursement.reimbId}`)
        row.appendChild(reimbNoButton);
    }

    // append the row into the table
    document.getElementById('reimbursement-table-body').appendChild(row);
}

function refreshTable() {
    fetch(`http://localhost:8080/ReimbursementApi/reimbursements`, { //
        credentials: 'include'
    })
        .then(res => res.json())
        .then(data => {
            let numRows = document.getElementById('reimbursement-table-body').rows.length
            for (let i = numRows - 1; i > -1; i--) {
                document.getElementById('reimbursement-table-body').deleteRow(i)
            }
            data.forEach(addReimbursementToTable)
            //console.log('refreshed')
        })
        .catch(console.log);
}

function refreshTableUS(nums) {
    fetch(`http://localhost:8080/ReimbursementApi/reimbursements?user=${nums[0]}&status=${nums[1]}`, { //
        credentials: 'include'
    })
        .then(res => res.json())
        .then(data => {
            let numRows = document.getElementById('reimbursement-table-body').rows.length
            // console.log(numRows)
            // console.log(`both here`)
            for (let i = numRows - 1; i > -1; i--) {
                document.getElementById('reimbursement-table-body').deleteRow(i)
            }
            data.forEach(addReimbursementToTable)
        })
        .catch(console.log);
}

function refreshTableU(user) {
    fetch(`http://localhost:8080/ReimbursementApi/reimbursements?user=${user}`, { //
        credentials: 'include'
    })
        .then(res => res.json())
        .then(data => {
            let numRows = document.getElementById('reimbursement-table-body').rows.length
            // console.log(numRows)
            // console.log(`user thing`)
            for (let i = numRows - 1; i > -1; i--) {
                document.getElementById('reimbursement-table-body').deleteRow(i)
            }
            data.forEach(addReimbursementToTable)
        })
        .catch(console.log);
}

function refreshTableS(status) {
    fetch(`http://localhost:8080/ReimbursementApi/reimbursements?status=${status}`, { //
        credentials: 'include'
    })
        .then(res => res.json())
        .then(data => {
            let numRows = document.getElementById('reimbursement-table-body').rows.length
            // console.log(numRows)
            // console.log(`status num = ${status}`)
            for (let i = numRows - 1; i > -1; i--) {
                document.getElementById('reimbursement-table-body').deleteRow(i)
            }
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
            //logout causes data to be null so it can't read username of it
            currentUser = data;
            if (currentUser.userRoleId === 1) {
                window.location = '/view-reimbursements.html'
            }
            //console.log(currentUser)
            refreshTable();

        })
        .catch(err => {
            console.log(err)
            window.location = '/login.html'
        })
}

function logout(event) {
    //event.preventDefault();
    console.log('logout function')
    fetch('http://localhost:8080/ReimbursementApi/auth/logout', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        mode: 'cors',
        credentials: 'include', // put credentials: 'include' on every request to use session info
        //body: JSON.stringify(credential)
    })
        .then(resp => {
            // if(resp.status === 201) {
            //     // redirect
            getCurrentUserInfo();
            //window.location = '/login.html';
            // } else {
            //     //document.getElementById('error-message').innerText = 'Failed to login';
            // }
        })
}

function approve(id) {
    event.preventDefault(); // stop page from refreshing
    console.log('updated');

    fetch(`http://localhost:8080/ReimbursementApi/reimbursements?status=2&resolver=${currentUser.ersUsersId}&id=${id}`, {
        method: 'PUT',

        headers: {
            'content-type': 'application/json'
        }
    })
    .then(resp => {
        if (resp.status === 204) {
            refreshTable()
        } else if (resp.status === 401) {
            alert('You cannot approve your own requests!')
        } else {
            console.log('Something went wrong')
        }
    })
        //.then(await refreshTable())

        .catch(err => console.log(err));

}

function deny(id) {
    event.preventDefault(); // stop page from refreshing
    console.log('updated');

    fetch(`http://localhost:8080/ReimbursementApi/reimbursements?status=3&resolver=${currentUser.ersUsersId}&id=${id}`, {
        method: 'PUT',

        headers: {
            'content-type': 'application/json'
        }
    })
    .then(resp => {
        if (resp.status === 204) {
            refreshTable()
        } else if (resp.status === 401) {
            alert('You cannot deny your own requests!')
        } else {
            console.log('Something went wrong')
        }
    })
        //.then(await refreshTable())

        .catch(err => console.log(err));

}

getCurrentUserInfo();


