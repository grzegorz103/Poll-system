import { Component, OnInit } from '@angular/core';
import { LocationStrategy, PlatformLocation, Location } from '@angular/common';
import { LegendItem, ChartType } from '../lbd/lbd-chart/lbd-chart.component';
import * as Chartist from 'chartist';
import { Poll, Vote } from 'app/poll-list/poll-list.component';
import { PollService } from 'app/poll-list/poll.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  poll: Poll;

  constructor(
    private pollService: PollService;
  ) {
    this.poll = new Poll();
    this.poll.votes = [];
  }

  ngOnInit() {

  }

  add(){
    this.poll.votes.push(new Vote());
  }

  save(){
    this.pollService.save(this.poll).subscribe(res=>alert("Thank u"));
  }
}
