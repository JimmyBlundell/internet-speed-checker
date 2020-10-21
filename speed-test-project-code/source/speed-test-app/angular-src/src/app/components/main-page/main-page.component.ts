import { Component, OnInit } from '@angular/core';
import { SpeedTestService } from '../../services/speed-test.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(private SpeedTestService : SpeedTestService, private router : Router, private _snackBar: MatSnackBar) { }

  speedData : any = {
    ping : {},
    download : {},
    upload : {}
  };
  uploadGauge = {
    gaugeType : "arch",
    gaugeValue : 0,
    gaugeLabel : "Upload Speed",
    gaugeAppendText : "Mbps"
  }

  downloadGauge = {
    gaugeType : "arch",
    gaugeValue : 0,
    gaugeLabel : "Download Speed",
    gaugeAppendText : "Mbps"
  }

  waiting : boolean = false;
  cancelWait : boolean = false

  gaugeType = "arch";
  gaugeValue = 28.3;
  gaugeLabel = "Speed";
  gaugeAppendText = "Mbps";

  ngOnInit(): void {
    
  }

  progreassBar () {
    if(!this.waiting) {
      this.waiting = true
    }
    else { 
      this.waiting = false
    }
  }

  getSpeed () {
    this.progreassBar()
    this._snackBar.open("Getting speed. Please wait", "", {
      duration: 2000,
    });
    this.SpeedTestService.checkSpeed()
      .then(res => {
        if(res.success) {
          this.speedData = res.speed;
          this.uploadGauge.gaugeValue = this.speedData.upload.bandwidth*0.00001;
          this.downloadGauge.gaugeValue = this.speedData.download.bandwidth*0.00001;
        console.log(this.speedData)
        }
        else {
          this._snackBar.open(res.err, "", {
            duration: 2000,
          });
        }
      })
      .finally(()=>{
        this.progreassBar()
      })
  }

  historyAndDetails () {
    this.router.navigate(['/history-details'])
  }

}
