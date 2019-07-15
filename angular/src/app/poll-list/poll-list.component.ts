import { Component, OnInit } from '@angular/core';
import { PollService } from './poll.service';
import { TimeAgoPipe } from 'angular2-moment';
import moment = require('moment');
import * as m from 'moment-timezone';

export class Poll {
  id: number;
  name: string;
  votes: Vote[];
  multipleAnswer: boolean;
  postDate: Date;
  allowSameIp: boolean;
  nonPublic: boolean;
  code: string;
}

export class Vote {
  id: number;
  name: string;
  voteCount: number;
  code: string;
}

export class PagePoll {
  content: Poll[];
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  first: boolean;
  sort: string;
  numberOfElements: number;
}

@Component({
  selector: 'app-poll-list',
  templateUrl: './poll-list.component.html',
  styleUrls: ['./poll-list.component.scss'],
})
export class PollListComponent implements OnInit {

  polls: Poll[];
  pagePoll: PagePoll;
  selectedPage: number = 0;
  serverTime: any;

  constructor(
    private pollService: PollService
  ) { }

  ngOnInit() {
    this.fetchTime();
    this.fetchData(0);
  }

  fetchData(page: number) {
    this.pollService.findAllPaged(page).subscribe(res => {
      this.pagePoll = res;
      let serverLocalDiff = moment(Date.now()).diff(this.serverTime);
      let minutesServerLocalDiff = moment.duration(serverLocalDiff).asMinutes();
      for (let i = 0; i < this.pagePoll.content.length; ++i) {
        let currentPoll = this.pagePoll.content[i];
        currentPoll.postDate = new Date(currentPoll.postDate.toString());
       currentPoll.postDate.setMinutes(currentPoll.postDate.getMinutes() + minutesServerLocalDiff);
      }
      //  this.polls.sort((o1, o2) => o2.postDate.toString().localeCompare(o1.postDate.toString()));
    });
  }

  fetchTime() {
    this.pollService.getTime().subscribe(res => {
      this.serverTime = new Date(res.toString());
    })
  }

  countVotes(poll: Poll) {
    let count = 0;
    if (poll != null) {
      poll.votes.forEach(e => count += e.voteCount);
    }
    return count;
  }

  onSelect(page: number) {
    this.selectedPage = page;
    this.fetchData(page);
  }
}
