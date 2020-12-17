import { Component, OnInit } from '@angular/core';

import { SpeedTestService } from '../../services/speed-test.service';

@Component({
  selector: 'app-history-details',
  templateUrl: './history-details.component.html',
  styleUrls: ['./history-details.component.css']
})
export class HistoryDetailsComponent implements OnInit {

  displayedColumns: string[] = ['timestamp', 'download', 'upload', 'ping'];
  dataSource = []

  constructor(private SpeedTestService : SpeedTestService) { }

  ngOnInit(): void {
    this.SpeedTestService.getHistory()
      .subscribe(res => {
        if(res.success ) {
          this.dataSource = res.history;
          console.log(res)
        }
        else { 

        }
      })
  }

  roundOff (num) {
    return num.toFixed(2)
  }

}
