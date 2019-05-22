import { Component, OnInit } from '@angular/core';
import { PoemService } from '../poem.service';
import { Poem } from 'src/app/Poem';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit {
  favPoem: Array<Poem>;
  getPoemLines:Array<Poem>
  poemlines:Array<string>;
  poemTitle:string;
  poem:Poem;
  poemUpdate:Poem;
  commentModal:boolean;

  constructor(private poemService: PoemService) { }

  ngOnInit() {
    this.poemService.getMyFavouritePoems().subscribe(data=>{
      console.log(data);
      this.favPoem=data;
    })
  }

  viewPoem(p){
    console.log(p);
    this.poemService.getPoemsByTitle(p['title']).subscribe(data=>{
      console.log(data);
       this.getPoemLines=data;
      this.poem=this.getPoemLines[0];
      this.poem.id=p['id'];
      this.poemTitle= this.poem.title;
     this.poemlines=this.poem.lines;
    })
  }

  delete(poem){
    this.poemService.deleteMyPoem(poem).subscribe(data=>{
      console.log("deleted");
    })
    const index = this.favPoem.indexOf(poem);
    this.favPoem.splice(index,1);
  }

  deleteLines(poem){
    this.poemService.deleteMyPoem(poem).subscribe(data=>{
      this.poemService.getMyFavouritePoems().subscribe(data=>{
        console.log(data);
        this.favPoem=data;
      });
    });
  }

  updatePoem(p:Poem){
  console.log(p);
  this.commentModal=true;
  this.poemUpdate=p;
  console.log(this.poemUpdate);
  this.poemService.updatePoem(this.poemUpdate).subscribe();
}
 
}
