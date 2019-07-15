import { Component, OnInit } from '@angular/core';
import { LocationStrategy, PlatformLocation, Location } from '@angular/common';
import { LegendItem, ChartType } from '../lbd/lbd-chart/lbd-chart.component';
import * as Chartist from 'chartist';
import { Poll, Vote } from 'app/poll-list/poll-list.component';
import { PollService } from 'app/poll-list/poll.service';
import { AuthService } from 'app/auth.service';

declare var $: any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  poll: Poll;

  constructor(
    private pollService: PollService,
    private authService: AuthService
  ) {
    this.poll = new Poll();
    this.poll.multipleAnswer = false;
    this.poll.allowSameIp = false;
    this.poll.nonPublic = false;
    this.poll.votes = [];
    this.poll.votes.push(new Vote());
  }

  ngOnInit() {
    $(function () {
      $('[data-toggle="tooltip"]').tooltip()
    })
  }

  add() {
    this.poll.votes.push(new Vote());
  }

  save() {
    if (this.poll.nonPublic && !this.authService.isAuthenticated) {
      this.showNotification('danger', 'Something went wrong', 'pe-7s-info');
    }
    else {
      this.pollService.save(this.poll).subscribe(res => {
        this.showNotification('success', 'Thank you for creating new poll!', 'pe-7s-smile');
      });
    }
  }

  showNotification(type, text, icon) {
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

removeAnswer(id: number) {
  if (this.poll.votes.length > 1) {
    this.poll.votes.splice(id, 1);
  }
}

changeMultipleAnswer() {
  alert('ok');
  this.poll.multipleAnswer = !this.poll.multipleAnswer;
}
}
