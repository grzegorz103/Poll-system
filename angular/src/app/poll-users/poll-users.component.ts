import { Component, OnInit } from '@angular/core';
import { Poll, PagePoll } from 'app/poll-list/poll-list.component';
import { PollService } from 'app/poll-list/poll.service';
import moment = require('moment');

declare var $: any;
@Component({
  selector: 'app-poll-users',
  templateUrl: './poll-users.component.html',
  styleUrls: ['./poll-users.component.scss']
})
export class PollUsersComponent implements OnInit {

  pagePoll: PagePoll;
  serverTime: any;
  selectedPage: number = 0;


  constructor(
    private pollService: PollService
  ) { }

  ngOnInit() {
    this.fetchData(0);
    this.fetchTime();
  }

  fetchData(page: number) {
    this.pollService.findByUser(page).subscribe(res => {
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

  updatePolls() {
    this.pollService.updateMany(this.pagePoll.content)
      .subscribe(res => {
        this.fetchData(this.selectedPage);
        this.showNotification('success', 'Changes saved', 'pe-7s-smile');
      });
  }

  showNotification(type: string, text: string, icon: string) {
    $.notify({
      icon: icon,
      message: text
    }, {
        type: type,
        timer: 1000,
        placement: {
          from: 'top',
          align: 'right'
        }
      });
  }
}
