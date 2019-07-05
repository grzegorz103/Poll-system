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

export class PagePoll{
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

  constructor(
    private pollService: PollService
  ) { }

  ngOnInit() {
    this.fetchData(0);
  }

  fetchData(page: number) {
    this.pollService.findAllPaged(page).subscribe(res => {
    this.pagePoll = res;
    //  this.polls.sort((o1, o2) => o2.postDate.toString().localeCompare(o1.postDate.toString()));
    });
  }

  countVotes(poll: Poll) {
    let count = 0;
    if (poll != null) {
      poll.votes.forEach(e => count += e.voteCount);
    }
    return count;
  }

  onSelect(page: number){
    this.selectedPage = page;
    this.fetchData(page);
  }
}
