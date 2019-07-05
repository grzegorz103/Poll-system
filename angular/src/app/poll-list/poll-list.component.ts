import { Component, OnInit } from '@angular/core';
import { PollService } from './poll.service';
import {TimeAgoPipe} from 'angular2-moment';

export class Poll {
  id: number;
  name: string;
  votes: Vote[];
  multipleAnswer: boolean;
  postDate: any;
  allowSameIp: boolean;
}

export class Vote {
  id: number;
  name: string;
  voteCount: number;
}
@Component({
  selector: 'app-poll-list',
  templateUrl: './poll-list.component.html',
  styleUrls: ['./poll-list.component.scss'],
})
export class PollListComponent implements OnInit {

  polls: Poll[];

  constructor(
    private pollService: PollService
  ) { }

  ngOnInit() {
    this.fetchData();
  }

  fetchData() {
    this.pollService.findAll().subscribe(res => {
    this.polls = res;
      this.polls.sort((o1, o2) => o2.postDate.toString().localeCompare(o1.postDate.toString()));
    });
  }

  countVotes(poll: Poll) {
    let count = 0;
    if (poll != null) {
      poll.votes.forEach(e => count += e.voteCount);
    }
    return count;
  }

}
