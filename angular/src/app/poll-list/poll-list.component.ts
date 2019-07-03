import { Component, OnInit } from '@angular/core';
import { PollService } from './poll.service';

export interface Poll{
  id: number;
  votes: Vote[];
}

interface Vote{
  id: number;
  name: string;
  voteCount: number;
}
@Component({
  selector: 'app-poll-list',
  templateUrl: './poll-list.component.html',
  styleUrls: ['./poll-list.component.scss']
})
export class PollListComponent implements OnInit {

  polls: Poll[];

  constructor(
    private pollService: PollService
  ) { }

  ngOnInit() {
    this.fetchData();
  }

  fetchData(){
    this.pollService.findAll().subscribe(res=>this.polls = res);
  }

  countVotes(poll: Poll){
    let count = 0;
    if(poll != null){
        poll.votes.forEach(e=> count += e.voteCount);
    }
    return count;
  }

}
