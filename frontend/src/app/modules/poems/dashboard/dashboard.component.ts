import { Component, OnInit } from '@angular/core';
import { PoemService } from '../poem.service';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Poem } from 'src/app/Poem';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  authors: Array<any>;
  poets: Array<string>;
  sonnets: Array<Poem>;
  poemTitleList:Array<Poem>
  poemlines:Array<string>;
  poemTitle:string;
  poem:Poem;
  poetsPoem:Array<Poem>;
  formdata:FormGroup;
  homepage: boolean;
  searchp: boolean;
  newPoem:Poem;
  showResponseModel:boolean;
error:any;
  constructor(private poemService: PoemService,private fb:FormBuilder) { }

  ngOnInit() {
    this.homepage=true;
    this.searchp=false;
    this.formdata = this.fb.group(
      {
        search : new FormControl()
      }
    )
    this.poemService.getAllAuthors().subscribe(data=>{
      this.poets=data['authors']
    })
    this.poemService.getSonnets().subscribe(data=>{
      this.sonnets=data;
      this.sonnets.splice(26,this.sonnets.length-26);
      console.log(this.sonnets); 
    })
  }

  searchPoems(){
    this.searchp=true;
    console.log(this.formdata.value.search);
    this.poemService.getPoemsByTitle(this.formdata.value.search).subscribe(data=>{
      this.sonnets=data;
    })
  }

  getTitles(poetName){
    this.homepage=false;
    this.searchp=true;
    console.log("poetname"+poetName);
    this.poemService.getTitles(poetName).subscribe(data=>{
      console.log(data);
      this.poemTitleList=data;
    })
  }

  show (data){
    console.log(data);
    this.poem=data;
    this.poemTitle=data.title;
   this.poemlines=this.poem.lines;
  }

  show2(keyword){
    console.log(keyword);
    this.poemService.getPoemsByTitle(keyword).subscribe(data=>{
    console.log(data);
      this.poetsPoem=data;
      this.poem=this.poetsPoem[0];
      this.poemTitle=this.poem.title;
      this.poemlines=this.poem.lines;
    })
  }

  addToFavourite(addPoem:Poem){
    console.log(addPoem);
    this.poemService.addToFavourites(addPoem).subscribe(data=>{
      this.error="Poem saved";
    },
    response=>{
      this.error=response.error.message
    })
  }
 

}
