import { Component, OnInit } from '@angular/core';
import { Pokemon } from 'src/app/model/pokemon.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-pokemon-table',
  templateUrl: './pokemon-table.component.html',
  styleUrls: ['./pokemon-table.component.scss']
})
export class PokemonTableComponent implements OnInit {

  pokemon: Pokemon[] = [];

  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
    this.httpClient.get<Pokemon[]>('http://localhost:8080/PokemonApi/pokemon', {
      withCredentials: true
    })
      .subscribe(data => {
        console.log(data);
        this.pokemon = data;
      }, err => {
        console.log(err);
      });
  }

}
