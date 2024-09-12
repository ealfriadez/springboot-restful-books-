import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Book } from '../model/book';
import { Observable } from 'rxjs';
 
@Injectable()
export class BookService {
 
  private booksUrl: string;
 
  constructor(private http: HttpClient) {
    this.booksUrl = 'http://localhost:8080/books';
  }
 
  public findAll(): Observable<Book[]> {
    return this.http.get<Book[]>(this.booksUrl);
  }
 
  public save(user: Book) {
    return this.http.post<Book>(this.booksUrl, user);
  }
}
