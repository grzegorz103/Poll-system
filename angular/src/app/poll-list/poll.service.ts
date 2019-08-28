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
    this.url = 'https://hot-poll.herokuapp.com/api/poll/';
  }

  findAll() {
    return this.http.get<Poll[]>(this.url);
  }

  findAllPaged(page: number) {
    let urlWithParam = 'https://hot-poll.herokuapp.com/api/poll?page=' + page + '&size=4';
    return this.http.get<PagePoll>(urlWithParam);
  }

  findByUser(page: number){
    let urlWithParam= 'https://hot-poll.herokuapp.com/api/poll/my/all?page=' + page + '&size=4';
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
  
  updateMany(polls: Poll[]) {
    return this.http.put(this.url + 'all', polls);
  }

  getTime(){
    return this.http.get('https://hot-poll.herokuapp.com/api/time');
  }
}
