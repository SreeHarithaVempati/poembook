import { TestBed, fakeAsync, inject } from '@angular/core/testing';

import { PoemService } from './poem.service';
import { Poem } from 'src/app/Poem';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthguardService } from 'src/app/authguard.service';
describe('PoemService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));
  let poemService :PoemService
  let poem:Poem;
  let str:string;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule, HttpClientTestingModule],
      providers: [PoemService, AuthguardService]
    });
    poemService = TestBed.get(PoemService);
    poem=new Poem();
    str="";
  });

  it('should be created poem Service',
    inject([poemService], (service: PoemService) => {
      expect(service).toBeTruthy();
    }));

  fit('should add to favorite poem list', fakeAsync(() => {
    inject([poemService, HttpTestingController], (backend: HttpTestingController) => {
      const mockReq = backend.expectOne(poemService.springEndPoint);
      expect(mockReq.request.url).toEqual(poemService.springEndPoint, 'request url should match with json server api url');
      expect(mockReq.request.method).toBe('POST', 'Should handle requested method type');
      backend.verify();
    });
    poemService.addToFavourites(poem).subscribe((res: any) => {
      expect(res).toBeDefined();
    });
  }));

  fit('should get all poems data from poetryDB', fakeAsync(() => {
    inject([poemService, HttpTestingController], (backend: HttpTestingController) => {
      const mockReq = backend.expectOne(poemService.poetryEndPoint);
      expect(mockReq.request.url).toEqual(poemService.poetryEndPoint, 'request url should match with json server api url');
      expect(mockReq.request.method).toBe('POST', 'Should handle requested method type');
      backend.verify();
    });
    poemService.getAllAuthors().subscribe((res: any) => {
      expect(res).toBeDefined();
    });
  }));
  fit('should get all favorite poems data', fakeAsync(() => {
    inject([poemService, HttpTestingController], (backend: HttpTestingController) => {
      const mockReq = backend.expectOne(poemService.springEndPoint);
      expect(mockReq.request.url).toEqual(poemService.springEndPoint, 'request url should match with json server api url');
      expect(mockReq.request.method).toBe('GET', 'Should handle requested method type');
      backend.verify();
    });
    poemService.getMyFavouritePoems().subscribe((res: any) => {
      expect(res).toBeDefined();
    });
  }));
  
});

