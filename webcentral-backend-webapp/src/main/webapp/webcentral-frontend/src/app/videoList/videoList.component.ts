import { VideoListItem } from './../videoListItem/videoListItem.service';
import { Component, OnInit, Injectable } from '@angular/core';
import { VideoList, VideoListService } from './videoList.service';

@Component({
  selector: 'wc-video-list',
  templateUrl: './videoList.component.html',
  styleUrls: ['./videoList.component.css']
})

@Injectable()
export class VideoListComponent implements OnInit {

  private videoListService: VideoListService;
  videos: VideoListItem[];

  constructor(videoListService: VideoListService) {
    this.videoListService = videoListService;
  }

  ngOnInit() {
    this.videoListService.getVideoList().subscribe((videoList: VideoList) => {
      this.videos = videoList.videos;
    });
  }

}