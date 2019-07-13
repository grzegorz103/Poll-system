import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Poll, PagePoll } from './poll-list.component';

@Injectable({
  providedIn: 'root'
})
export class PollService {

  url: string;

  constructor(
    private http: HttpClient
  ) {
    this.url = '/poll/';
  }

  findAll() {
    return this.http.get<Poll[]>(this.url);
  }

  findAllPaged(page: number) {
    let urlWithParam = '/poll?page=' + page + '&size=4';
    return this.http.get<PagePoll>(urlWithParam);
  }

  findById(id: number) {
    return this.http.get<Poll>(this.url + id);
  }

  vote(votes: number[]) {
    return this.http.put(this.url, votes);
  }

  save(poll: Poll) {
    return this.http.post(this.url, poll);
  }
}
