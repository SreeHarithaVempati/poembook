import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Poem } from 'src/app/Poem';

@Injectable({
  providedIn: 'root'
})
export class PoemService {
  poetryEndPoint:string;
  springEndPoint:string;

  constructor(private http: HttpClient) {
    this.poetryEndPoint="http://poetrydb.org/author";
    this.springEndPoint="http://localhost:5655/api/v1/favouritelistservice";
   }

   getAllAuthors(){
  return   this.http.get<Array<any>>(this.poetryEndPoint);
   }

   getSonnets(){
    return this.http.get<Array<any>>("http://poetrydb.org/author,title/Shakespeare;Sonnet");
     }

    getPoemsByTitle(keyword){
      console.log("in service",keyword);
      return this.http.get<Array<any>>("http://poetrydb.org/title/"+keyword);
    }

    getTitles(authorName){
      return this.http.get<Array<any>>("http://poetrydb.org/author/"+authorName+"/title")
    }

    addToFavourites(addPoem){
      console.log(addPoem);
      return this.http.post(this.springEndPoint+'/poem',addPoem);
    }

    getMyFavouritePoems(){
      return this.http.get<Array<any>>(this.springEndPoint+'/poem');
    }

    deleteMyPoem(poem){
      return this.http.delete(this.springEndPoint+'/poem/'+poem.id);
    }

    updatePoem(poem){
      return this.http.put<Poem>(this.springEndPoint+'/poem/'+poem.id,poem);
    }
}
