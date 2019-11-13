function login(event) {
    event.preventDefault();

    const ersUsername = document.getElementById('inputUsername').value;
    const ersPassword = document.getElementById('inputPassword').value;
    const credential = {
        ersUsername,
        ersPassword
    };

    fetch('http://localhost:8080/ReimbursementApi/auth/login', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        mode: 'cors',
        credentials: 'include', // put credentials: 'include' on every request to use session info
        body: JSON.stringify(credential)
    })
        .then(resp => {
            if (resp.status === 201) {
                // redirect
                window.location = '/view-reimbursements.html';
            } else {
                document.getElementById('error-message').innerText = 'Failed to login';
            }
        })


}