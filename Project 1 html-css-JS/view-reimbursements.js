let currentUser;
function newReimbursementSubmit(event) {
    event.preventDefault(); // stop page from refreshing
    console.log('submitted');
    
    const pokemon = getReimbursementFromInputs();

    fetch('http://localhost:8080/ReimbursementApi/reimbursements', {
        method: 'POST',
        body: JSON.stringify(pokemon),
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

function addReimbursementToTable(pokemon) {

    // create the row element
    const row = document.createElement('tr');

    // create all the td elements and append them to the row
    const nameData = document.createElement('td');
    nameData.innerText = pokemon.name;
    row.appendChild(nameData);

    const typeData = document.createElement('td');
    typeData.innerText = pokemon.type.name;
    row.appendChild(typeData);

    const hpData = document.createElement('td');
    hpData.innerText = pokemon.healthPoints;
    row.appendChild(hpData);

    const levelData = document.createElement('td');
    levelData.innerText = pokemon.level;
    row.appendChild(levelData);

    const trainerData = document.createElement('td');
    trainerData.innerText = pokemon.trainer.username;
    row.appendChild(trainerData);

    // append the row into the table
    document.getElementById('pokemon-table-body').appendChild(row);
}



function getPokemonFromInputs() {
    const pokemonName = document.getElementById('pokemon-name-input').value;
    const pokemonHp = document.getElementById('pokemon-hp-input').value;
    const pokemonLevel = document.getElementById('pokemon-level-input').value;
    const pokemonType = document.getElementById('pokemon-type-select').value;

    const pokemon = {
        name: pokemonName,
        healthPoints: pokemonHp,
        level: pokemonLevel,
        type: {
            id: 5, // should probably find a way to get the correct id
            name: pokemonType
        },
        trainer: currentUser
    }
    return pokemon;
}

function refreshTable() {
    fetch('http://localhost:8080/ReimbursementApi/reimbursements',{
        credentials: 'include'
    })
        .then(res => res.json())
        .then(data => {
            data.forEach(addPokemonToTableSafe)
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
        refreshTable();
        currentUser = data;
    })
    .catch(err => {
    })
}

getCurrentUserInfo();