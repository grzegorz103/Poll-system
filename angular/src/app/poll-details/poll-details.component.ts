import { Component, OnInit } from '@angular/core';
import { PollService } from 'app/poll-list/poll.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Poll } from 'app/poll-list/poll-list.component';
import { ChartType } from 'app/lbd/lbd-chart/lbd-chart.component';
import * as Chartist from 'chartist';
import * as MyLegend from 'chartist-plugin-legend';

declare var $: any;

@Component({
  selector: 'app-poll-details',
  templateUrl: './poll-details.component.html',
  styleUrls: ['./poll-details.component.scss']
})
export class PollDetailsComponent implements OnInit {

  urlId: number;
  poll: Poll;
  // selectedItem: number;
  selectedItems: boolean[];
  chartType: any;
  chartData: any;
  chartLegendItems: any;
  public activityChartOptions: any;
  public activityChartResponsive: any[];
  colors: any;
  barChart: any;
  barChartOptions: any;
  barChartData: any;
  display: boolean;
  success: boolean;

  constructor(
    private pollService: PollService,
    private router: Router,
    private rt: ActivatedRoute
  ) {
    var tester = new MyLegend();
    this.colors = ['#1DC7EA', '#FB404B', '#FFA534', '#9368E9', '#87CB16', '#1F77D0', '#5e5e5e', '#dd4b39', '#35465c', '#e52d27', '#55acee', '#cc2127', '#1769ff', '#6188e2', '#a748ca'];
    this.barChart = ChartType.Bar;
  }

  ngOnInit() {
    this.rt.params.subscribe(params => {
      this.urlId = params['id'];
      this.fetchData(false);
    });
  }

  fetchData(forceDisplay: boolean) {
    this.pollService.findById(this.urlId).subscribe(res => {
      this.poll = res;
      this.poll.votes.sort((o1, o2) => o1.name.localeCompare(o2.name));
      let voteCount = 0;
      if (this.poll !== null) {
        this.selectedItems = [];
        this.poll.votes.forEach(e => {
          voteCount += e.voteCount;
        });
        if (voteCount > 0) {
          let labels = [];
          let series = [];
          let votes = [];
          for (let i = 0; i < this.poll.votes.length; ++i) {
            //      this.chartData.push((this.poll.votes[i].voteCount / voteCount) * 100);
            let percent = Math.round((this.poll.votes[i].voteCount / voteCount) * 100);
            let label = this.poll.votes[i].name.trim();
            labels.push(label.length > 15 ? label.substring(0, 15) + ' ' + percent + '%' : label + ' ' + percent + '%');
            series.push(percent);
            votes.push(this.poll.votes[i].voteCount);
            this.selectedItems.push(false);
          }
          this.chartType = ChartType.Pie;
          this.chartData = { labels, series };
          // this.chartLegendItems = [
          // { title: 'Open', imageClass: 'fa fa-circle text-info' },
          //{ title: 'Bounce', imageClass: 'fa fa-circle text-danger' },
          //{ title: 'Unsubscribe', imageClass: 'fa fa-circle text-warning' }
          // ];
          // for (let i = 0; i < this.poll.votes.length; ++i) {
          //   this.chartLegendItems.push({ title: this.poll.votes[i].name, imageClass: 'fa fa-circle color' + i });
          // }
          this.activityChartOptions = {
            height: '245px',
            //  plugins: [Chartist.plugins.legend()]
          };
          this.activityChartResponsive = [
            ['screen and (max-width: 640px)', {
              seriesBarDistance: 5,
              axisX: {
                labelInterpolationFnc: function (value) {
                  return value[0];
                }
              }
            }]
          ];
          console.log(series);
          this.barChartData = { labels, series: [votes] };
          this.barChartOptions = {
            seriesBarDistance: 10,
            axisX: {
              showGrid: false
            },
            height: '245px'
          };
        }
      }
      if(forceDisplay){
        this.display = true;
      }
    });
  }

  vote() {
    if (!this.poll.multipleAnswer && this.selectedItems.filter(e => e).length > 1) {
      this.showNotification('danger', 'Something went wrong', 'pe-7s-info');
      return;
    }
    if (this.selectedItems.every(e => !e)) {
      this.showNotification('danger', 'You haven\'t selected any option!', 'pe-7s-info');
      return;
    }
    let selectedVotes = [];
    for (let i = 0; i < this.poll.votes.length; ++i) {
      if (this.selectedItems[i]) {
        selectedVotes.push(this.poll.votes[i].code);
        /* this.pollService.vote(this.poll.votes[i].id).subscribe(res => this.success = true, err =>this.success = false);
        if (!this.success) {
          this.showNotification('danger', 'You have already voted in this poll!', 'pe-7s-info');
          return;
        }*/
      }
    }
    this.pollService.vote(selectedVotes).subscribe(res => {
      this.showNotification('success', 'Thank you for voting', 'pe-7s-smile');
      this.display = false;
      this.fetchData(true);
    }
      ,
      err => this.showNotification('danger', 'You have alredy voted in this poll!', 'pe-7s-info'));
    //  this.showNotification('success', 'Thank you for voting', 'pe-7s-smile');
  }

  displayStats() {
    this.display = !this.display;
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

  asd(id: number) {
    if (!this.poll.multipleAnswer && this.selectedItems.filter(e => e === true).length > 0) {
      this.selectedItems[id] = false;
      alert(this.selectedItems.filter(e => e === true).length);
      this.showNotification('danger', 'You can\'t select more than one option!', 'pe-7s-info');
    } else {
      this.selectedItems[id] = true;
    }
  }

  isDisabled(id: number) {
    return !this.poll.multipleAnswer && !this.selectedItems[id] && this.selectedItems.filter(e => e).length > 0;
  }
}
