package cv.detector.fast;

import java.awt.image.BufferedImage;

import cv.common.Filter;

public class Fast12 {
	
	public static FastStruct detect(BufferedImage bImage, int threshold)
	{
		int[] xs = new int[2000];
		int[] ys = new int[2000];
		int[] scores = new int[2000];
		int count = 0;
		
		long start = System.currentTimeMillis();
		bImage = Filter.grayScale(bImage);
		System.out.println("Time for grayscale: " + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		int h = bImage.getHeight();
		int w = bImage.getWidth();
		int[][] image = new int [h][w];
		for (int y = 0; y < h; ++y) {
			for (int x = 0; x < w; ++x) {
				image[y][x] = bImage.getRGB(x, y) & 0xFF;
			}
		}
		System.out.println("Time for copy: " + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		for (int y = 4; y < h - 4; ++y) {
			for (int x = 4; x < w - 4; ++x) {
				int cb = image[y][x] + threshold;
				int c_b = image[y][x] - threshold;
				if (image[y+3][x+0] > cb) 
				 if (image[y+3][x+1] > cb) 
				  if (image[y+2][x+2] > cb) 
				   if (image[y+1][x+3] > cb) 
				    if (image[y+0][x+3] > cb) 
				     if (image[y+-1][x+3] > cb) 
				      if (image[y+-2][x+2] > cb) 
				       if (image[y+-3][x+1] > cb) 
				        if (image[y+-3][x+0] > cb) 
				         if (image[y+-3][x+-1] > cb) 
				          if (image[y+-2][x+-2] > cb) 
				           if (image[y+-1][x+-3] > cb) 
				            {;}
				           else
				            if (image[y+3][x+-1] > cb) 
				             {;}
				            else
				             continue;
				          else
				           if (image[y+2][x+-2] > cb) 
				            if (image[y+3][x+-1] > cb) 
				             {;}
				            else
				             continue;
				           else
				            continue;
				         else
				          if (image[y+1][x+-3] > cb) 
				           if (image[y+2][x+-2] > cb) 
				            if (image[y+3][x+-1] > cb) 
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				        else
				         if (image[y+0][x+-3] > cb) 
				          if (image[y+1][x+-3] > cb) 
				           if (image[y+2][x+-2] > cb) 
				            if (image[y+3][x+-1] > cb) 
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				       else
				        if (image[y+-1][x+-3] > cb) 
				         if (image[y+0][x+-3] > cb) 
				          if (image[y+1][x+-3] > cb) 
				           if (image[y+2][x+-2] > cb) 
				            if (image[y+3][x+-1] > cb) 
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				      else
				       if (image[y+-2][x+-2] > cb) 
				        if (image[y+-1][x+-3] > cb) 
				         if (image[y+0][x+-3] > cb) 
				          if (image[y+1][x+-3] > cb) 
				           if (image[y+2][x+-2] > cb) 
				            if (image[y+3][x+-1] > cb) 
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				     else
				      if (image[y+-3][x+-1] > cb) 
				       if (image[y+-2][x+-2] > cb) 
				        if (image[y+-1][x+-3] > cb) 
				         if (image[y+0][x+-3] > cb) 
				          if (image[y+1][x+-3] > cb) 
				           if (image[y+2][x+-2] > cb) 
				            if (image[y+3][x+-1] > cb) 
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				    else if (image[y+0][x+3] < c_b) 
				     if (image[y+-3][x+0] > cb) 
				      if (image[y+-3][x+-1] > cb) 
				       if (image[y+-2][x+-2] > cb) 
				        if (image[y+-1][x+-3] > cb) 
				         if (image[y+0][x+-3] > cb) 
				          if (image[y+1][x+-3] > cb) 
				           if (image[y+2][x+-2] > cb) 
				            if (image[y+3][x+-1] > cb) 
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else if (image[y+-3][x+0] < c_b) 
				      if (image[y+-1][x+3] < c_b)
				       if (image[y+-2][x+2] < c_b)
				        if (image[y+-3][x+1] < c_b)
				         if (image[y+-3][x+-1] < c_b)
				          if (image[y+-2][x+-2] < c_b)
				           if (image[y+-1][x+-3] < c_b)
				            if (image[y+0][x+-3] < c_b)
				             if (image[y+1][x+-3] < c_b)
				              if (image[y+2][x+-2] < c_b)
				               if (image[y+3][x+-1] < c_b)
				                {;}
				               else
				                continue;
				              else
				               continue;
				             else
				              continue;
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     if (image[y+-3][x+0] > cb) 
				      if (image[y+-3][x+-1] > cb) 
				       if (image[y+-2][x+-2] > cb) 
				        if (image[y+-1][x+-3] > cb) 
				         if (image[y+0][x+-3] > cb) 
				          if (image[y+1][x+-3] > cb) 
				           if (image[y+2][x+-2] > cb) 
				            if (image[y+3][x+-1] > cb) 
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				   else if (image[y+1][x+3] < c_b) 
				    if (image[y+3][x+-1] > cb) 
				     if (image[y+-3][x+1] > cb) 
				      if (image[y+-3][x+0] > cb) 
				       if (image[y+-3][x+-1] > cb) 
				        if (image[y+-2][x+-2] > cb) 
				         if (image[y+-1][x+-3] > cb) 
				          if (image[y+0][x+-3] > cb) 
				           if (image[y+1][x+-3] > cb) 
				            if (image[y+2][x+-2] > cb) 
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else if (image[y+-3][x+1] < c_b) 
				      if (image[y+0][x+3] < c_b)
				       if (image[y+-1][x+3] < c_b)
				        if (image[y+-2][x+2] < c_b)
				         if (image[y+-3][x+0] < c_b)
				          if (image[y+-3][x+-1] < c_b)
				           if (image[y+-2][x+-2] < c_b)
				            if (image[y+-1][x+-3] < c_b)
				             if (image[y+0][x+-3] < c_b)
				              if (image[y+1][x+-3] < c_b)
				               if (image[y+2][x+-2] < c_b)
				                {;}
				               else
				                continue;
				              else
				               continue;
				             else
				              continue;
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     if (image[y+0][x+3] < c_b)
				      if (image[y+-1][x+3] < c_b)
				       if (image[y+-2][x+2] < c_b)
				        if (image[y+-3][x+1] < c_b)
				         if (image[y+-3][x+0] < c_b)
				          if (image[y+-3][x+-1] < c_b)
				           if (image[y+-2][x+-2] < c_b)
				            if (image[y+-1][x+-3] < c_b)
				             if (image[y+0][x+-3] < c_b)
				              if (image[y+1][x+-3] < c_b)
				               if (image[y+2][x+-2] < c_b)
				                {;}
				               else
				                continue;
				              else
				               continue;
				             else
				              continue;
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				   else
				    if (image[y+-3][x+1] > cb) 
				     if (image[y+-3][x+0] > cb) 
				      if (image[y+-3][x+-1] > cb) 
				       if (image[y+-2][x+-2] > cb) 
				        if (image[y+-1][x+-3] > cb) 
				         if (image[y+0][x+-3] > cb) 
				          if (image[y+1][x+-3] > cb) 
				           if (image[y+2][x+-2] > cb) 
				            if (image[y+3][x+-1] > cb) 
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else if (image[y+-3][x+1] < c_b) 
				     if (image[y+0][x+3] < c_b)
				      if (image[y+-1][x+3] < c_b)
				       if (image[y+-2][x+2] < c_b)
				        if (image[y+-3][x+0] < c_b)
				         if (image[y+-3][x+-1] < c_b)
				          if (image[y+-2][x+-2] < c_b)
				           if (image[y+-1][x+-3] < c_b)
				            if (image[y+0][x+-3] < c_b)
				             if (image[y+1][x+-3] < c_b)
				              if (image[y+2][x+-2] < c_b)
				               if (image[y+3][x+-1] < c_b)
				                {;}
				               else
				                continue;
				              else
				               continue;
				             else
				              continue;
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				  else if (image[y+2][x+2] < c_b) 
				   if (image[y+-2][x+2] > cb) 
				    if (image[y+-3][x+1] > cb) 
				     if (image[y+-3][x+0] > cb) 
				      if (image[y+-3][x+-1] > cb) 
				       if (image[y+-2][x+-2] > cb) 
				        if (image[y+-1][x+-3] > cb) 
				         if (image[y+0][x+-3] > cb) 
				          if (image[y+1][x+-3] > cb) 
				           if (image[y+2][x+-2] > cb) 
				            if (image[y+3][x+-1] > cb) 
				             {;}
				            else
				             if (image[y+1][x+3] > cb) 
				              if (image[y+0][x+3] > cb) 
				               if (image[y+-1][x+3] > cb) 
				                {;}
				               else
				                continue;
				              else
				               continue;
				             else
				              continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else if (image[y+-2][x+2] < c_b) 
				    if (image[y+0][x+3] < c_b)
				     if (image[y+-1][x+3] < c_b)
				      if (image[y+-3][x+1] < c_b)
				       if (image[y+-3][x+0] < c_b)
				        if (image[y+-3][x+-1] < c_b)
				         if (image[y+-2][x+-2] < c_b)
				          if (image[y+-1][x+-3] < c_b)
				           if (image[y+0][x+-3] < c_b)
				            if (image[y+1][x+-3] < c_b)
				             if (image[y+1][x+3] < c_b)
				              {;}
				             else
				              if (image[y+2][x+-2] < c_b)
				               if (image[y+3][x+-1] < c_b)
				                {;}
				               else
				                continue;
				              else
				               continue;
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else
				    continue;
				  else
				   if (image[y+-2][x+2] > cb) 
				    if (image[y+-3][x+1] > cb) 
				     if (image[y+-3][x+0] > cb) 
				      if (image[y+-3][x+-1] > cb) 
				       if (image[y+-2][x+-2] > cb) 
				        if (image[y+-1][x+-3] > cb) 
				         if (image[y+0][x+-3] > cb) 
				          if (image[y+1][x+-3] > cb) 
				           if (image[y+2][x+-2] > cb) 
				            if (image[y+3][x+-1] > cb) 
				             {;}
				            else
				             if (image[y+1][x+3] > cb) 
				              if (image[y+0][x+3] > cb) 
				               if (image[y+-1][x+3] > cb) 
				                {;}
				               else
				                continue;
				              else
				               continue;
				             else
				              continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else if (image[y+-2][x+2] < c_b) 
				    if (image[y+0][x+3] < c_b)
				     if (image[y+-1][x+3] < c_b)
				      if (image[y+-3][x+1] < c_b)
				       if (image[y+-3][x+0] < c_b)
				        if (image[y+-3][x+-1] < c_b)
				         if (image[y+-2][x+-2] < c_b)
				          if (image[y+-1][x+-3] < c_b)
				           if (image[y+0][x+-3] < c_b)
				            if (image[y+1][x+-3] < c_b)
				             if (image[y+2][x+-2] < c_b)
				              if (image[y+1][x+3] < c_b)
				               {;}
				              else
				               if (image[y+3][x+-1] < c_b)
				                {;}
				               else
				                continue;
				             else
				              continue;
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else
				    continue;
				 else if (image[y+3][x+1] < c_b) 
				  if (image[y+-1][x+3] > cb) 
				   if (image[y+-2][x+2] > cb) 
				    if (image[y+-3][x+1] > cb) 
				     if (image[y+-3][x+0] > cb) 
				      if (image[y+-3][x+-1] > cb) 
				       if (image[y+-2][x+-2] > cb) 
				        if (image[y+-1][x+-3] > cb) 
				         if (image[y+0][x+-3] > cb) 
				          if (image[y+1][x+-3] > cb) 
				           if (image[y+2][x+-2] > cb) 
				            if (image[y+3][x+-1] > cb) 
				             {;}
				            else
				             if (image[y+1][x+3] > cb) 
				              if (image[y+0][x+3] > cb) 
				               {;}
				              else
				               continue;
				             else
				              continue;
				           else
				            if (image[y+2][x+2] > cb) 
				             if (image[y+1][x+3] > cb) 
				              if (image[y+0][x+3] > cb) 
				               {;}
				              else
				               continue;
				             else
				              continue;
				            else
				             continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else
				    continue;
				  else if (image[y+-1][x+3] < c_b) 
				   if (image[y+0][x+3] < c_b)
				    if (image[y+-2][x+2] < c_b)
				     if (image[y+-3][x+1] < c_b)
				      if (image[y+-3][x+0] < c_b)
				       if (image[y+-3][x+-1] < c_b)
				        if (image[y+-2][x+-2] < c_b)
				         if (image[y+-1][x+-3] < c_b)
				          if (image[y+0][x+-3] < c_b)
				           if (image[y+1][x+3] < c_b)
				            if (image[y+2][x+2] < c_b)
				             {;}
				            else
				             if (image[y+1][x+-3] < c_b)
				              if (image[y+2][x+-2] < c_b)
				               {;}
				              else
				               continue;
				             else
				              continue;
				           else
				            if (image[y+1][x+-3] < c_b)
				             if (image[y+2][x+-2] < c_b)
				              if (image[y+3][x+-1] < c_b)
				               {;}
				              else
				               continue;
				             else
				              continue;
				            else
				             continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else
				    continue;
				  else
				   continue;
				 else
				  if (image[y+-1][x+3] > cb) 
				   if (image[y+-2][x+2] > cb) 
				    if (image[y+-3][x+1] > cb) 
				     if (image[y+-3][x+0] > cb) 
				      if (image[y+-3][x+-1] > cb) 
				       if (image[y+-2][x+-2] > cb) 
				        if (image[y+-1][x+-3] > cb) 
				         if (image[y+0][x+-3] > cb) 
				          if (image[y+1][x+-3] > cb) 
				           if (image[y+2][x+-2] > cb) 
				            if (image[y+3][x+-1] > cb) 
				             {;}
				            else
				             if (image[y+1][x+3] > cb) 
				              if (image[y+0][x+3] > cb) 
				               {;}
				              else
				               continue;
				             else
				              continue;
				           else
				            if (image[y+2][x+2] > cb) 
				             if (image[y+1][x+3] > cb) 
				              if (image[y+0][x+3] > cb) 
				               {;}
				              else
				               continue;
				             else
				              continue;
				            else
				             continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else
				    continue;
				  else if (image[y+-1][x+3] < c_b) 
				   if (image[y+0][x+3] < c_b)
				    if (image[y+-2][x+2] < c_b)
				     if (image[y+-3][x+1] < c_b)
				      if (image[y+-3][x+0] < c_b)
				       if (image[y+-3][x+-1] < c_b)
				        if (image[y+-2][x+-2] < c_b)
				         if (image[y+-1][x+-3] < c_b)
				          if (image[y+0][x+-3] < c_b)
				           if (image[y+1][x+-3] < c_b)
				            if (image[y+1][x+3] < c_b)
				             if (image[y+2][x+2] < c_b)
				              {;}
				             else
				              if (image[y+2][x+-2] < c_b)
				               {;}
				              else
				               continue;
				            else
				             if (image[y+2][x+-2] < c_b)
				              if (image[y+3][x+-1] < c_b)
				               {;}
				              else
				               continue;
				             else
				              continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else
				    continue;
				  else
				   continue;
				else if (image[y+3][x+0] < c_b) 
				 if (image[y+3][x+1] > cb) 
				  if (image[y+-1][x+3] > cb) 
				   if (image[y+0][x+3] > cb) 
				    if (image[y+-2][x+2] > cb) 
				     if (image[y+-3][x+1] > cb) 
				      if (image[y+-3][x+0] > cb) 
				       if (image[y+-3][x+-1] > cb) 
				        if (image[y+-2][x+-2] > cb) 
				         if (image[y+-1][x+-3] > cb) 
				          if (image[y+0][x+-3] > cb) 
				           if (image[y+1][x+3] > cb) 
				            if (image[y+2][x+2] > cb) 
				             {;}
				            else
				             if (image[y+1][x+-3] > cb) 
				              if (image[y+2][x+-2] > cb) 
				               {;}
				              else
				               continue;
				             else
				              continue;
				           else
				            if (image[y+1][x+-3] > cb) 
				             if (image[y+2][x+-2] > cb) 
				              if (image[y+3][x+-1] > cb) 
				               {;}
				              else
				               continue;
				             else
				              continue;
				            else
				             continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else
				    continue;
				  else if (image[y+-1][x+3] < c_b) 
				   if (image[y+-2][x+2] < c_b)
				    if (image[y+-3][x+1] < c_b)
				     if (image[y+-3][x+0] < c_b)
				      if (image[y+-3][x+-1] < c_b)
				       if (image[y+-2][x+-2] < c_b)
				        if (image[y+-1][x+-3] < c_b)
				         if (image[y+0][x+-3] < c_b)
				          if (image[y+1][x+-3] < c_b)
				           if (image[y+2][x+-2] < c_b)
				            if (image[y+3][x+-1] < c_b)
				             {;}
				            else
				             if (image[y+1][x+3] < c_b)
				              if (image[y+0][x+3] < c_b)
				               {;}
				              else
				               continue;
				             else
				              continue;
				           else
				            if (image[y+2][x+2] < c_b)
				             if (image[y+1][x+3] < c_b)
				              if (image[y+0][x+3] < c_b)
				               {;}
				              else
				               continue;
				             else
				              continue;
				            else
				             continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else
				    continue;
				  else
				   continue;
				 else if (image[y+3][x+1] < c_b) 
				  if (image[y+2][x+2] > cb) 
				   if (image[y+-2][x+2] > cb) 
				    if (image[y+0][x+3] > cb) 
				     if (image[y+-1][x+3] > cb) 
				      if (image[y+-3][x+1] > cb) 
				       if (image[y+-3][x+0] > cb) 
				        if (image[y+-3][x+-1] > cb) 
				         if (image[y+-2][x+-2] > cb) 
				          if (image[y+-1][x+-3] > cb) 
				           if (image[y+0][x+-3] > cb) 
				            if (image[y+1][x+-3] > cb) 
				             if (image[y+1][x+3] > cb) 
				              {;}
				             else
				              if (image[y+2][x+-2] > cb) 
				               if (image[y+3][x+-1] > cb) 
				                {;}
				               else
				                continue;
				              else
				               continue;
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else if (image[y+-2][x+2] < c_b) 
				    if (image[y+-3][x+1] < c_b)
				     if (image[y+-3][x+0] < c_b)
				      if (image[y+-3][x+-1] < c_b)
				       if (image[y+-2][x+-2] < c_b)
				        if (image[y+-1][x+-3] < c_b)
				         if (image[y+0][x+-3] < c_b)
				          if (image[y+1][x+-3] < c_b)
				           if (image[y+2][x+-2] < c_b)
				            if (image[y+3][x+-1] < c_b)
				             {;}
				            else
				             if (image[y+1][x+3] < c_b)
				              if (image[y+0][x+3] < c_b)
				               if (image[y+-1][x+3] < c_b)
				                {;}
				               else
				                continue;
				              else
				               continue;
				             else
				              continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else
				    continue;
				  else if (image[y+2][x+2] < c_b) 
				   if (image[y+1][x+3] > cb) 
				    if (image[y+3][x+-1] < c_b)
				     if (image[y+-3][x+1] > cb) 
				      if (image[y+0][x+3] > cb) 
				       if (image[y+-1][x+3] > cb) 
				        if (image[y+-2][x+2] > cb) 
				         if (image[y+-3][x+0] > cb) 
				          if (image[y+-3][x+-1] > cb) 
				           if (image[y+-2][x+-2] > cb) 
				            if (image[y+-1][x+-3] > cb) 
				             if (image[y+0][x+-3] > cb) 
				              if (image[y+1][x+-3] > cb) 
				               if (image[y+2][x+-2] > cb) 
				                {;}
				               else
				                continue;
				              else
				               continue;
				             else
				              continue;
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else if (image[y+-3][x+1] < c_b) 
				      if (image[y+-3][x+0] < c_b)
				       if (image[y+-3][x+-1] < c_b)
				        if (image[y+-2][x+-2] < c_b)
				         if (image[y+-1][x+-3] < c_b)
				          if (image[y+0][x+-3] < c_b)
				           if (image[y+1][x+-3] < c_b)
				            if (image[y+2][x+-2] < c_b)
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     if (image[y+0][x+3] > cb) 
				      if (image[y+-1][x+3] > cb) 
				       if (image[y+-2][x+2] > cb) 
				        if (image[y+-3][x+1] > cb) 
				         if (image[y+-3][x+0] > cb) 
				          if (image[y+-3][x+-1] > cb) 
				           if (image[y+-2][x+-2] > cb) 
				            if (image[y+-1][x+-3] > cb) 
				             if (image[y+0][x+-3] > cb) 
				              if (image[y+1][x+-3] > cb) 
				               if (image[y+2][x+-2] > cb) 
				                {;}
				               else
				                continue;
				              else
				               continue;
				             else
				              continue;
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				   else if (image[y+1][x+3] < c_b) 
				    if (image[y+0][x+3] > cb) 
				     if (image[y+-3][x+0] > cb) 
				      if (image[y+-1][x+3] > cb) 
				       if (image[y+-2][x+2] > cb) 
				        if (image[y+-3][x+1] > cb) 
				         if (image[y+-3][x+-1] > cb) 
				          if (image[y+-2][x+-2] > cb) 
				           if (image[y+-1][x+-3] > cb) 
				            if (image[y+0][x+-3] > cb) 
				             if (image[y+1][x+-3] > cb) 
				              if (image[y+2][x+-2] > cb) 
				               if (image[y+3][x+-1] > cb) 
				                {;}
				               else
				                continue;
				              else
				               continue;
				             else
				              continue;
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else if (image[y+-3][x+0] < c_b) 
				      if (image[y+-3][x+-1] < c_b)
				       if (image[y+-2][x+-2] < c_b)
				        if (image[y+-1][x+-3] < c_b)
				         if (image[y+0][x+-3] < c_b)
				          if (image[y+1][x+-3] < c_b)
				           if (image[y+2][x+-2] < c_b)
				            if (image[y+3][x+-1] < c_b)
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else if (image[y+0][x+3] < c_b) 
				     if (image[y+-1][x+3] < c_b)
				      if (image[y+-2][x+2] < c_b)
				       if (image[y+-3][x+1] < c_b)
				        if (image[y+-3][x+0] < c_b)
				         if (image[y+-3][x+-1] < c_b)
				          if (image[y+-2][x+-2] < c_b)
				           if (image[y+-1][x+-3] < c_b)
				            {;}
				           else
				            if (image[y+3][x+-1] < c_b)
				             {;}
				            else
				             continue;
				          else
				           if (image[y+2][x+-2] < c_b)
				            if (image[y+3][x+-1] < c_b)
				             {;}
				            else
				             continue;
				           else
				            continue;
				         else
				          if (image[y+1][x+-3] < c_b)
				           if (image[y+2][x+-2] < c_b)
				            if (image[y+3][x+-1] < c_b)
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				        else
				         if (image[y+0][x+-3] < c_b)
				          if (image[y+1][x+-3] < c_b)
				           if (image[y+2][x+-2] < c_b)
				            if (image[y+3][x+-1] < c_b)
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				       else
				        if (image[y+-1][x+-3] < c_b)
				         if (image[y+0][x+-3] < c_b)
				          if (image[y+1][x+-3] < c_b)
				           if (image[y+2][x+-2] < c_b)
				            if (image[y+3][x+-1] < c_b)
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				      else
				       if (image[y+-2][x+-2] < c_b)
				        if (image[y+-1][x+-3] < c_b)
				         if (image[y+0][x+-3] < c_b)
				          if (image[y+1][x+-3] < c_b)
				           if (image[y+2][x+-2] < c_b)
				            if (image[y+3][x+-1] < c_b)
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				     else
				      if (image[y+-3][x+-1] < c_b)
				       if (image[y+-2][x+-2] < c_b)
				        if (image[y+-1][x+-3] < c_b)
				         if (image[y+0][x+-3] < c_b)
				          if (image[y+1][x+-3] < c_b)
				           if (image[y+2][x+-2] < c_b)
				            if (image[y+3][x+-1] < c_b)
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				    else
				     if (image[y+-3][x+0] < c_b)
				      if (image[y+-3][x+-1] < c_b)
				       if (image[y+-2][x+-2] < c_b)
				        if (image[y+-1][x+-3] < c_b)
				         if (image[y+0][x+-3] < c_b)
				          if (image[y+1][x+-3] < c_b)
				           if (image[y+2][x+-2] < c_b)
				            if (image[y+3][x+-1] < c_b)
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				   else
				    if (image[y+-3][x+1] > cb) 
				     if (image[y+0][x+3] > cb) 
				      if (image[y+-1][x+3] > cb) 
				       if (image[y+-2][x+2] > cb) 
				        if (image[y+-3][x+0] > cb) 
				         if (image[y+-3][x+-1] > cb) 
				          if (image[y+-2][x+-2] > cb) 
				           if (image[y+-1][x+-3] > cb) 
				            if (image[y+0][x+-3] > cb) 
				             if (image[y+1][x+-3] > cb) 
				              if (image[y+2][x+-2] > cb) 
				               if (image[y+3][x+-1] > cb) 
				                {;}
				               else
				                continue;
				              else
				               continue;
				             else
				              continue;
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else if (image[y+-3][x+1] < c_b) 
				     if (image[y+-3][x+0] < c_b)
				      if (image[y+-3][x+-1] < c_b)
				       if (image[y+-2][x+-2] < c_b)
				        if (image[y+-1][x+-3] < c_b)
				         if (image[y+0][x+-3] < c_b)
				          if (image[y+1][x+-3] < c_b)
				           if (image[y+2][x+-2] < c_b)
				            if (image[y+3][x+-1] < c_b)
				             {;}
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				  else
				   if (image[y+-2][x+2] > cb) 
				    if (image[y+0][x+3] > cb) 
				     if (image[y+-1][x+3] > cb) 
				      if (image[y+-3][x+1] > cb) 
				       if (image[y+-3][x+0] > cb) 
				        if (image[y+-3][x+-1] > cb) 
				         if (image[y+-2][x+-2] > cb) 
				          if (image[y+-1][x+-3] > cb) 
				           if (image[y+0][x+-3] > cb) 
				            if (image[y+1][x+-3] > cb) 
				             if (image[y+2][x+-2] > cb) 
				              if (image[y+1][x+3] > cb) 
				               {;}
				              else
				               if (image[y+3][x+-1] > cb) 
				                {;}
				               else
				                continue;
				             else
				              continue;
				            else
				             continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else if (image[y+-2][x+2] < c_b) 
				    if (image[y+-3][x+1] < c_b)
				     if (image[y+-3][x+0] < c_b)
				      if (image[y+-3][x+-1] < c_b)
				       if (image[y+-2][x+-2] < c_b)
				        if (image[y+-1][x+-3] < c_b)
				         if (image[y+0][x+-3] < c_b)
				          if (image[y+1][x+-3] < c_b)
				           if (image[y+2][x+-2] < c_b)
				            if (image[y+3][x+-1] < c_b)
				             {;}
				            else
				             if (image[y+1][x+3] < c_b)
				              if (image[y+0][x+3] < c_b)
				               if (image[y+-1][x+3] < c_b)
				                {;}
				               else
				                continue;
				              else
				               continue;
				             else
				              continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else
				    continue;
				 else
				  if (image[y+-1][x+3] > cb) 
				   if (image[y+0][x+3] > cb) 
				    if (image[y+-2][x+2] > cb) 
				     if (image[y+-3][x+1] > cb) 
				      if (image[y+-3][x+0] > cb) 
				       if (image[y+-3][x+-1] > cb) 
				        if (image[y+-2][x+-2] > cb) 
				         if (image[y+-1][x+-3] > cb) 
				          if (image[y+0][x+-3] > cb) 
				           if (image[y+1][x+-3] > cb) 
				            if (image[y+1][x+3] > cb) 
				             if (image[y+2][x+2] > cb) 
				              {;}
				             else
				              if (image[y+2][x+-2] > cb) 
				               {;}
				              else
				               continue;
				            else
				             if (image[y+2][x+-2] > cb) 
				              if (image[y+3][x+-1] > cb) 
				               {;}
				              else
				               continue;
				             else
				              continue;
				           else
				            continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else
				    continue;
				  else if (image[y+-1][x+3] < c_b) 
				   if (image[y+-2][x+2] < c_b)
				    if (image[y+-3][x+1] < c_b)
				     if (image[y+-3][x+0] < c_b)
				      if (image[y+-3][x+-1] < c_b)
				       if (image[y+-2][x+-2] < c_b)
				        if (image[y+-1][x+-3] < c_b)
				         if (image[y+0][x+-3] < c_b)
				          if (image[y+1][x+-3] < c_b)
				           if (image[y+2][x+-2] < c_b)
				            if (image[y+3][x+-1] < c_b)
				             {;}
				            else
				             if (image[y+1][x+3] < c_b)
				              if (image[y+0][x+3] < c_b)
				               {;}
				              else
				               continue;
				             else
				              continue;
				           else
				            if (image[y+2][x+2] < c_b)
				             if (image[y+1][x+3] < c_b)
				              if (image[y+0][x+3] < c_b)
				               {;}
				              else
				               continue;
				             else
				              continue;
				            else
				             continue;
				          else
				           continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else
				    continue;
				  else
				   continue;
				else
				 if (image[y+0][x+3] > cb) 
				  if (image[y+-1][x+3] > cb) 
				   if (image[y+-2][x+2] > cb) 
				    if (image[y+-3][x+1] > cb) 
				     if (image[y+-3][x+0] > cb) 
				      if (image[y+-3][x+-1] > cb) 
				       if (image[y+-2][x+-2] > cb) 
				        if (image[y+-1][x+-3] > cb) 
				         if (image[y+0][x+-3] > cb) 
				          if (image[y+1][x+3] > cb) 
				           if (image[y+2][x+2] > cb) 
				            if (image[y+3][x+1] > cb) 
				             {;}
				            else
				             if (image[y+1][x+-3] > cb) 
				              {;}
				             else
				              continue;
				           else
				            if (image[y+1][x+-3] > cb) 
				             if (image[y+2][x+-2] > cb) 
				              {;}
				             else
				              continue;
				            else
				             continue;
				          else
				           if (image[y+1][x+-3] > cb) 
				            if (image[y+2][x+-2] > cb) 
				             if (image[y+3][x+-1] > cb) 
				              {;}
				             else
				              continue;
				            else
				             continue;
				           else
				            continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else
				    continue;
				  else
				   continue;
				 else if (image[y+0][x+3] < c_b) 
				  if (image[y+-1][x+3] < c_b)
				   if (image[y+-2][x+2] < c_b)
				    if (image[y+-3][x+1] < c_b)
				     if (image[y+-3][x+0] < c_b)
				      if (image[y+-3][x+-1] < c_b)
				       if (image[y+-2][x+-2] < c_b)
				        if (image[y+-1][x+-3] < c_b)
				         if (image[y+0][x+-3] < c_b)
				          if (image[y+1][x+3] < c_b)
				           if (image[y+2][x+2] < c_b)
				            if (image[y+3][x+1] < c_b)
				             {;}
				            else
				             if (image[y+1][x+-3] < c_b)
				              {;}
				             else
				              continue;
				           else
				            if (image[y+1][x+-3] < c_b)
				             if (image[y+2][x+-2] < c_b)
				              {;}
				             else
				              continue;
				            else
				             continue;
				          else
				           if (image[y+1][x+-3] < c_b)
				            if (image[y+2][x+-2] < c_b)
				             if (image[y+3][x+-1] < c_b)
				              {;}
				             else
				              continue;
				            else
				             continue;
				           else
				            continue;
				         else
				          continue;
				        else
				         continue;
				       else
				        continue;
				      else
				       continue;
				     else
				      continue;
				    else
				     continue;
				   else
				    continue;
				  else
				   continue;
				 else
				  continue;
				count++;
				xs[count] = x;
				ys[count] = y;
			}
		}
		//for (int i = 0; i < count; ++i) {
		//	scores[i] = cornerScore(image, xs[i], ys[i]);
		//}
		System.out.println("Time for FAST: " + (System.currentTimeMillis() - start));
		return new FastStruct(xs, ys, scores, count);
	}
	
	private static int cornerScore(int[][] image, int x, int y)
	{
		int bmin = 0;
		int bmax = 255;
		int b = (bmax + bmin)/2;
	    
		while (true)
		{
			if (isCorner(image, x, y, b)) {
				bmin = b;
			} else {
				bmax = b;
			}
	        
			if (bmin == bmax - 1 || bmin == bmax) {
				return bmin;
			}

			b = (bmin + bmax) / 2;
		}
	}
	
	private static boolean isCorner(int[][] i, int posx, int posy, int b)
	{		
		int cb = i[posy][posx] + b;
		int c_b = i[posy][posx] - b;
		if (i[posy+3][posx+0] > cb) 
		 if (i[posy+3][posx+1] > cb) 
		  if (i[posy+2][posx+2] > cb) 
		   if (i[posy+1][posx+3] > cb) 
		    if (i[posy+0][posx+3] > cb) 
		     if (i[posy+-1][posx+3] > cb) 
		      if (i[posy+-2][posx+2] > cb) 
		       if (i[posy+-3][posx+1] > cb) 
		        if (i[posy+-3][posx+0] > cb) 
		         if (i[posy+-3][posx+-1] > cb) 
		          if (i[posy+-2][posx+-2] > cb) 
		           if (i[posy+-1][posx+-3] > cb) 
		            return true;
		           else
		            if (i[posy+3][posx+-1] > cb) 
		             return true;
		            else
		             return false;
		          else
		           if (i[posy+2][posx+-2] > cb) 
		            if (i[posy+3][posx+-1] > cb) 
		             return true;
		            else
		             return false;
		           else
		            return false;
		         else
		          if (i[posy+1][posx+-3] > cb) 
		           if (i[posy+2][posx+-2] > cb) 
		            if (i[posy+3][posx+-1] > cb) 
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		        else
		         if (i[posy+0][posx+-3] > cb) 
		          if (i[posy+1][posx+-3] > cb) 
		           if (i[posy+2][posx+-2] > cb) 
		            if (i[posy+3][posx+-1] > cb) 
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		       else
		        if (i[posy+-1][posx+-3] > cb) 
		         if (i[posy+0][posx+-3] > cb) 
		          if (i[posy+1][posx+-3] > cb) 
		           if (i[posy+2][posx+-2] > cb) 
		            if (i[posy+3][posx+-1] > cb) 
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		      else
		       if (i[posy+-2][posx+-2] > cb) 
		        if (i[posy+-1][posx+-3] > cb) 
		         if (i[posy+0][posx+-3] > cb) 
		          if (i[posy+1][posx+-3] > cb) 
		           if (i[posy+2][posx+-2] > cb) 
		            if (i[posy+3][posx+-1] > cb) 
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		     else
		      if (i[posy+-3][posx+-1] > cb) 
		       if (i[posy+-2][posx+-2] > cb) 
		        if (i[posy+-1][posx+-3] > cb) 
		         if (i[posy+0][posx+-3] > cb) 
		          if (i[posy+1][posx+-3] > cb) 
		           if (i[posy+2][posx+-2] > cb) 
		            if (i[posy+3][posx+-1] > cb) 
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		    else if (i[posy+0][posx+3] < c_b) 
		     if (i[posy+-3][posx+0] > cb) 
		      if (i[posy+-3][posx+-1] > cb) 
		       if (i[posy+-2][posx+-2] > cb) 
		        if (i[posy+-1][posx+-3] > cb) 
		         if (i[posy+0][posx+-3] > cb) 
		          if (i[posy+1][posx+-3] > cb) 
		           if (i[posy+2][posx+-2] > cb) 
		            if (i[posy+3][posx+-1] > cb) 
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else if (i[posy+-3][posx+0] < c_b) 
		      if (i[posy+-1][posx+3] < c_b)
		       if (i[posy+-2][posx+2] < c_b)
		        if (i[posy+-3][posx+1] < c_b)
		         if (i[posy+-3][posx+-1] < c_b)
		          if (i[posy+-2][posx+-2] < c_b)
		           if (i[posy+-1][posx+-3] < c_b)
		            if (i[posy+0][posx+-3] < c_b)
		             if (i[posy+1][posx+-3] < c_b)
		              if (i[posy+2][posx+-2] < c_b)
		               if (i[posy+3][posx+-1] < c_b)
		                return true;
		               else
		                return false;
		              else
		               return false;
		             else
		              return false;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     if (i[posy+-3][posx+0] > cb) 
		      if (i[posy+-3][posx+-1] > cb) 
		       if (i[posy+-2][posx+-2] > cb) 
		        if (i[posy+-1][posx+-3] > cb) 
		         if (i[posy+0][posx+-3] > cb) 
		          if (i[posy+1][posx+-3] > cb) 
		           if (i[posy+2][posx+-2] > cb) 
		            if (i[posy+3][posx+-1] > cb) 
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		   else if (i[posy+1][posx+3] < c_b) 
		    if (i[posy+3][posx+-1] > cb) 
		     if (i[posy+-3][posx+1] > cb) 
		      if (i[posy+-3][posx+0] > cb) 
		       if (i[posy+-3][posx+-1] > cb) 
		        if (i[posy+-2][posx+-2] > cb) 
		         if (i[posy+-1][posx+-3] > cb) 
		          if (i[posy+0][posx+-3] > cb) 
		           if (i[posy+1][posx+-3] > cb) 
		            if (i[posy+2][posx+-2] > cb) 
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else if (i[posy+-3][posx+1] < c_b) 
		      if (i[posy+0][posx+3] < c_b)
		       if (i[posy+-1][posx+3] < c_b)
		        if (i[posy+-2][posx+2] < c_b)
		         if (i[posy+-3][posx+0] < c_b)
		          if (i[posy+-3][posx+-1] < c_b)
		           if (i[posy+-2][posx+-2] < c_b)
		            if (i[posy+-1][posx+-3] < c_b)
		             if (i[posy+0][posx+-3] < c_b)
		              if (i[posy+1][posx+-3] < c_b)
		               if (i[posy+2][posx+-2] < c_b)
		                return true;
		               else
		                return false;
		              else
		               return false;
		             else
		              return false;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     if (i[posy+0][posx+3] < c_b)
		      if (i[posy+-1][posx+3] < c_b)
		       if (i[posy+-2][posx+2] < c_b)
		        if (i[posy+-3][posx+1] < c_b)
		         if (i[posy+-3][posx+0] < c_b)
		          if (i[posy+-3][posx+-1] < c_b)
		           if (i[posy+-2][posx+-2] < c_b)
		            if (i[posy+-1][posx+-3] < c_b)
		             if (i[posy+0][posx+-3] < c_b)
		              if (i[posy+1][posx+-3] < c_b)
		               if (i[posy+2][posx+-2] < c_b)
		                return true;
		               else
		                return false;
		              else
		               return false;
		             else
		              return false;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		   else
		    if (i[posy+-3][posx+1] > cb) 
		     if (i[posy+-3][posx+0] > cb) 
		      if (i[posy+-3][posx+-1] > cb) 
		       if (i[posy+-2][posx+-2] > cb) 
		        if (i[posy+-1][posx+-3] > cb) 
		         if (i[posy+0][posx+-3] > cb) 
		          if (i[posy+1][posx+-3] > cb) 
		           if (i[posy+2][posx+-2] > cb) 
		            if (i[posy+3][posx+-1] > cb) 
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else if (i[posy+-3][posx+1] < c_b) 
		     if (i[posy+0][posx+3] < c_b)
		      if (i[posy+-1][posx+3] < c_b)
		       if (i[posy+-2][posx+2] < c_b)
		        if (i[posy+-3][posx+0] < c_b)
		         if (i[posy+-3][posx+-1] < c_b)
		          if (i[posy+-2][posx+-2] < c_b)
		           if (i[posy+-1][posx+-3] < c_b)
		            if (i[posy+0][posx+-3] < c_b)
		             if (i[posy+1][posx+-3] < c_b)
		              if (i[posy+2][posx+-2] < c_b)
		               if (i[posy+3][posx+-1] < c_b)
		                return true;
		               else
		                return false;
		              else
		               return false;
		             else
		              return false;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		  else if (i[posy+2][posx+2] < c_b) 
		   if (i[posy+-2][posx+2] > cb) 
		    if (i[posy+-3][posx+1] > cb) 
		     if (i[posy+-3][posx+0] > cb) 
		      if (i[posy+-3][posx+-1] > cb) 
		       if (i[posy+-2][posx+-2] > cb) 
		        if (i[posy+-1][posx+-3] > cb) 
		         if (i[posy+0][posx+-3] > cb) 
		          if (i[posy+1][posx+-3] > cb) 
		           if (i[posy+2][posx+-2] > cb) 
		            if (i[posy+3][posx+-1] > cb) 
		             return true;
		            else
		             if (i[posy+1][posx+3] > cb) 
		              if (i[posy+0][posx+3] > cb) 
		               if (i[posy+-1][posx+3] > cb) 
		                return true;
		               else
		                return false;
		              else
		               return false;
		             else
		              return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else if (i[posy+-2][posx+2] < c_b) 
		    if (i[posy+0][posx+3] < c_b)
		     if (i[posy+-1][posx+3] < c_b)
		      if (i[posy+-3][posx+1] < c_b)
		       if (i[posy+-3][posx+0] < c_b)
		        if (i[posy+-3][posx+-1] < c_b)
		         if (i[posy+-2][posx+-2] < c_b)
		          if (i[posy+-1][posx+-3] < c_b)
		           if (i[posy+0][posx+-3] < c_b)
		            if (i[posy+1][posx+-3] < c_b)
		             if (i[posy+1][posx+3] < c_b)
		              return true;
		             else
		              if (i[posy+2][posx+-2] < c_b)
		               if (i[posy+3][posx+-1] < c_b)
		                return true;
		               else
		                return false;
		              else
		               return false;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else
		    return false;
		  else
		   if (i[posy+-2][posx+2] > cb) 
		    if (i[posy+-3][posx+1] > cb) 
		     if (i[posy+-3][posx+0] > cb) 
		      if (i[posy+-3][posx+-1] > cb) 
		       if (i[posy+-2][posx+-2] > cb) 
		        if (i[posy+-1][posx+-3] > cb) 
		         if (i[posy+0][posx+-3] > cb) 
		          if (i[posy+1][posx+-3] > cb) 
		           if (i[posy+2][posx+-2] > cb) 
		            if (i[posy+3][posx+-1] > cb) 
		             return true;
		            else
		             if (i[posy+1][posx+3] > cb) 
		              if (i[posy+0][posx+3] > cb) 
		               if (i[posy+-1][posx+3] > cb) 
		                return true;
		               else
		                return false;
		              else
		               return false;
		             else
		              return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else if (i[posy+-2][posx+2] < c_b) 
		    if (i[posy+0][posx+3] < c_b)
		     if (i[posy+-1][posx+3] < c_b)
		      if (i[posy+-3][posx+1] < c_b)
		       if (i[posy+-3][posx+0] < c_b)
		        if (i[posy+-3][posx+-1] < c_b)
		         if (i[posy+-2][posx+-2] < c_b)
		          if (i[posy+-1][posx+-3] < c_b)
		           if (i[posy+0][posx+-3] < c_b)
		            if (i[posy+1][posx+-3] < c_b)
		             if (i[posy+2][posx+-2] < c_b)
		              if (i[posy+1][posx+3] < c_b)
		               return true;
		              else
		               if (i[posy+3][posx+-1] < c_b)
		                return true;
		               else
		                return false;
		             else
		              return false;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else
		    return false;
		 else if (i[posy+3][posx+1] < c_b) 
		  if (i[posy+-1][posx+3] > cb) 
		   if (i[posy+-2][posx+2] > cb) 
		    if (i[posy+-3][posx+1] > cb) 
		     if (i[posy+-3][posx+0] > cb) 
		      if (i[posy+-3][posx+-1] > cb) 
		       if (i[posy+-2][posx+-2] > cb) 
		        if (i[posy+-1][posx+-3] > cb) 
		         if (i[posy+0][posx+-3] > cb) 
		          if (i[posy+1][posx+-3] > cb) 
		           if (i[posy+2][posx+-2] > cb) 
		            if (i[posy+3][posx+-1] > cb) 
		             return true;
		            else
		             if (i[posy+1][posx+3] > cb) 
		              if (i[posy+0][posx+3] > cb) 
		               return true;
		              else
		               return false;
		             else
		              return false;
		           else
		            if (i[posy+2][posx+2] > cb) 
		             if (i[posy+1][posx+3] > cb) 
		              if (i[posy+0][posx+3] > cb) 
		               return true;
		              else
		               return false;
		             else
		              return false;
		            else
		             return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else
		    return false;
		  else if (i[posy+-1][posx+3] < c_b) 
		   if (i[posy+0][posx+3] < c_b)
		    if (i[posy+-2][posx+2] < c_b)
		     if (i[posy+-3][posx+1] < c_b)
		      if (i[posy+-3][posx+0] < c_b)
		       if (i[posy+-3][posx+-1] < c_b)
		        if (i[posy+-2][posx+-2] < c_b)
		         if (i[posy+-1][posx+-3] < c_b)
		          if (i[posy+0][posx+-3] < c_b)
		           if (i[posy+1][posx+3] < c_b)
		            if (i[posy+2][posx+2] < c_b)
		             return true;
		            else
		             if (i[posy+1][posx+-3] < c_b)
		              if (i[posy+2][posx+-2] < c_b)
		               return true;
		              else
		               return false;
		             else
		              return false;
		           else
		            if (i[posy+1][posx+-3] < c_b)
		             if (i[posy+2][posx+-2] < c_b)
		              if (i[posy+3][posx+-1] < c_b)
		               return true;
		              else
		               return false;
		             else
		              return false;
		            else
		             return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else
		    return false;
		  else
		   return false;
		 else
		  if (i[posy+-1][posx+3] > cb) 
		   if (i[posy+-2][posx+2] > cb) 
		    if (i[posy+-3][posx+1] > cb) 
		     if (i[posy+-3][posx+0] > cb) 
		      if (i[posy+-3][posx+-1] > cb) 
		       if (i[posy+-2][posx+-2] > cb) 
		        if (i[posy+-1][posx+-3] > cb) 
		         if (i[posy+0][posx+-3] > cb) 
		          if (i[posy+1][posx+-3] > cb) 
		           if (i[posy+2][posx+-2] > cb) 
		            if (i[posy+3][posx+-1] > cb) 
		             return true;
		            else
		             if (i[posy+1][posx+3] > cb) 
		              if (i[posy+0][posx+3] > cb) 
		               return true;
		              else
		               return false;
		             else
		              return false;
		           else
		            if (i[posy+2][posx+2] > cb) 
		             if (i[posy+1][posx+3] > cb) 
		              if (i[posy+0][posx+3] > cb) 
		               return true;
		              else
		               return false;
		             else
		              return false;
		            else
		             return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else
		    return false;
		  else if (i[posy+-1][posx+3] < c_b) 
		   if (i[posy+0][posx+3] < c_b)
		    if (i[posy+-2][posx+2] < c_b)
		     if (i[posy+-3][posx+1] < c_b)
		      if (i[posy+-3][posx+0] < c_b)
		       if (i[posy+-3][posx+-1] < c_b)
		        if (i[posy+-2][posx+-2] < c_b)
		         if (i[posy+-1][posx+-3] < c_b)
		          if (i[posy+0][posx+-3] < c_b)
		           if (i[posy+1][posx+-3] < c_b)
		            if (i[posy+1][posx+3] < c_b)
		             if (i[posy+2][posx+2] < c_b)
		              return true;
		             else
		              if (i[posy+2][posx+-2] < c_b)
		               return true;
		              else
		               return false;
		            else
		             if (i[posy+2][posx+-2] < c_b)
		              if (i[posy+3][posx+-1] < c_b)
		               return true;
		              else
		               return false;
		             else
		              return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else
		    return false;
		  else
		   return false;
		else if (i[posy+3][posx+0] < c_b) 
		 if (i[posy+3][posx+1] > cb) 
		  if (i[posy+-1][posx+3] > cb) 
		   if (i[posy+0][posx+3] > cb) 
		    if (i[posy+-2][posx+2] > cb) 
		     if (i[posy+-3][posx+1] > cb) 
		      if (i[posy+-3][posx+0] > cb) 
		       if (i[posy+-3][posx+-1] > cb) 
		        if (i[posy+-2][posx+-2] > cb) 
		         if (i[posy+-1][posx+-3] > cb) 
		          if (i[posy+0][posx+-3] > cb) 
		           if (i[posy+1][posx+3] > cb) 
		            if (i[posy+2][posx+2] > cb) 
		             return true;
		            else
		             if (i[posy+1][posx+-3] > cb) 
		              if (i[posy+2][posx+-2] > cb) 
		               return true;
		              else
		               return false;
		             else
		              return false;
		           else
		            if (i[posy+1][posx+-3] > cb) 
		             if (i[posy+2][posx+-2] > cb) 
		              if (i[posy+3][posx+-1] > cb) 
		               return true;
		              else
		               return false;
		             else
		              return false;
		            else
		             return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else
		    return false;
		  else if (i[posy+-1][posx+3] < c_b) 
		   if (i[posy+-2][posx+2] < c_b)
		    if (i[posy+-3][posx+1] < c_b)
		     if (i[posy+-3][posx+0] < c_b)
		      if (i[posy+-3][posx+-1] < c_b)
		       if (i[posy+-2][posx+-2] < c_b)
		        if (i[posy+-1][posx+-3] < c_b)
		         if (i[posy+0][posx+-3] < c_b)
		          if (i[posy+1][posx+-3] < c_b)
		           if (i[posy+2][posx+-2] < c_b)
		            if (i[posy+3][posx+-1] < c_b)
		             return true;
		            else
		             if (i[posy+1][posx+3] < c_b)
		              if (i[posy+0][posx+3] < c_b)
		               return true;
		              else
		               return false;
		             else
		              return false;
		           else
		            if (i[posy+2][posx+2] < c_b)
		             if (i[posy+1][posx+3] < c_b)
		              if (i[posy+0][posx+3] < c_b)
		               return true;
		              else
		               return false;
		             else
		              return false;
		            else
		             return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else
		    return false;
		  else
		   return false;
		 else if (i[posy+3][posx+1] < c_b) 
		  if (i[posy+2][posx+2] > cb) 
		   if (i[posy+-2][posx+2] > cb) 
		    if (i[posy+0][posx+3] > cb) 
		     if (i[posy+-1][posx+3] > cb) 
		      if (i[posy+-3][posx+1] > cb) 
		       if (i[posy+-3][posx+0] > cb) 
		        if (i[posy+-3][posx+-1] > cb) 
		         if (i[posy+-2][posx+-2] > cb) 
		          if (i[posy+-1][posx+-3] > cb) 
		           if (i[posy+0][posx+-3] > cb) 
		            if (i[posy+1][posx+-3] > cb) 
		             if (i[posy+1][posx+3] > cb) 
		              return true;
		             else
		              if (i[posy+2][posx+-2] > cb) 
		               if (i[posy+3][posx+-1] > cb) 
		                return true;
		               else
		                return false;
		              else
		               return false;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else if (i[posy+-2][posx+2] < c_b) 
		    if (i[posy+-3][posx+1] < c_b)
		     if (i[posy+-3][posx+0] < c_b)
		      if (i[posy+-3][posx+-1] < c_b)
		       if (i[posy+-2][posx+-2] < c_b)
		        if (i[posy+-1][posx+-3] < c_b)
		         if (i[posy+0][posx+-3] < c_b)
		          if (i[posy+1][posx+-3] < c_b)
		           if (i[posy+2][posx+-2] < c_b)
		            if (i[posy+3][posx+-1] < c_b)
		             return true;
		            else
		             if (i[posy+1][posx+3] < c_b)
		              if (i[posy+0][posx+3] < c_b)
		               if (i[posy+-1][posx+3] < c_b)
		                return true;
		               else
		                return false;
		              else
		               return false;
		             else
		              return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else
		    return false;
		  else if (i[posy+2][posx+2] < c_b) 
		   if (i[posy+1][posx+3] > cb) 
		    if (i[posy+3][posx+-1] < c_b)
		     if (i[posy+-3][posx+1] > cb) 
		      if (i[posy+0][posx+3] > cb) 
		       if (i[posy+-1][posx+3] > cb) 
		        if (i[posy+-2][posx+2] > cb) 
		         if (i[posy+-3][posx+0] > cb) 
		          if (i[posy+-3][posx+-1] > cb) 
		           if (i[posy+-2][posx+-2] > cb) 
		            if (i[posy+-1][posx+-3] > cb) 
		             if (i[posy+0][posx+-3] > cb) 
		              if (i[posy+1][posx+-3] > cb) 
		               if (i[posy+2][posx+-2] > cb) 
		                return true;
		               else
		                return false;
		              else
		               return false;
		             else
		              return false;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else if (i[posy+-3][posx+1] < c_b) 
		      if (i[posy+-3][posx+0] < c_b)
		       if (i[posy+-3][posx+-1] < c_b)
		        if (i[posy+-2][posx+-2] < c_b)
		         if (i[posy+-1][posx+-3] < c_b)
		          if (i[posy+0][posx+-3] < c_b)
		           if (i[posy+1][posx+-3] < c_b)
		            if (i[posy+2][posx+-2] < c_b)
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     if (i[posy+0][posx+3] > cb) 
		      if (i[posy+-1][posx+3] > cb) 
		       if (i[posy+-2][posx+2] > cb) 
		        if (i[posy+-3][posx+1] > cb) 
		         if (i[posy+-3][posx+0] > cb) 
		          if (i[posy+-3][posx+-1] > cb) 
		           if (i[posy+-2][posx+-2] > cb) 
		            if (i[posy+-1][posx+-3] > cb) 
		             if (i[posy+0][posx+-3] > cb) 
		              if (i[posy+1][posx+-3] > cb) 
		               if (i[posy+2][posx+-2] > cb) 
		                return true;
		               else
		                return false;
		              else
		               return false;
		             else
		              return false;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		   else if (i[posy+1][posx+3] < c_b) 
		    if (i[posy+0][posx+3] > cb) 
		     if (i[posy+-3][posx+0] > cb) 
		      if (i[posy+-1][posx+3] > cb) 
		       if (i[posy+-2][posx+2] > cb) 
		        if (i[posy+-3][posx+1] > cb) 
		         if (i[posy+-3][posx+-1] > cb) 
		          if (i[posy+-2][posx+-2] > cb) 
		           if (i[posy+-1][posx+-3] > cb) 
		            if (i[posy+0][posx+-3] > cb) 
		             if (i[posy+1][posx+-3] > cb) 
		              if (i[posy+2][posx+-2] > cb) 
		               if (i[posy+3][posx+-1] > cb) 
		                return true;
		               else
		                return false;
		              else
		               return false;
		             else
		              return false;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else if (i[posy+-3][posx+0] < c_b) 
		      if (i[posy+-3][posx+-1] < c_b)
		       if (i[posy+-2][posx+-2] < c_b)
		        if (i[posy+-1][posx+-3] < c_b)
		         if (i[posy+0][posx+-3] < c_b)
		          if (i[posy+1][posx+-3] < c_b)
		           if (i[posy+2][posx+-2] < c_b)
		            if (i[posy+3][posx+-1] < c_b)
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else if (i[posy+0][posx+3] < c_b) 
		     if (i[posy+-1][posx+3] < c_b)
		      if (i[posy+-2][posx+2] < c_b)
		       if (i[posy+-3][posx+1] < c_b)
		        if (i[posy+-3][posx+0] < c_b)
		         if (i[posy+-3][posx+-1] < c_b)
		          if (i[posy+-2][posx+-2] < c_b)
		           if (i[posy+-1][posx+-3] < c_b)
		            return true;
		           else
		            if (i[posy+3][posx+-1] < c_b)
		             return true;
		            else
		             return false;
		          else
		           if (i[posy+2][posx+-2] < c_b)
		            if (i[posy+3][posx+-1] < c_b)
		             return true;
		            else
		             return false;
		           else
		            return false;
		         else
		          if (i[posy+1][posx+-3] < c_b)
		           if (i[posy+2][posx+-2] < c_b)
		            if (i[posy+3][posx+-1] < c_b)
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		        else
		         if (i[posy+0][posx+-3] < c_b)
		          if (i[posy+1][posx+-3] < c_b)
		           if (i[posy+2][posx+-2] < c_b)
		            if (i[posy+3][posx+-1] < c_b)
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		       else
		        if (i[posy+-1][posx+-3] < c_b)
		         if (i[posy+0][posx+-3] < c_b)
		          if (i[posy+1][posx+-3] < c_b)
		           if (i[posy+2][posx+-2] < c_b)
		            if (i[posy+3][posx+-1] < c_b)
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		      else
		       if (i[posy+-2][posx+-2] < c_b)
		        if (i[posy+-1][posx+-3] < c_b)
		         if (i[posy+0][posx+-3] < c_b)
		          if (i[posy+1][posx+-3] < c_b)
		           if (i[posy+2][posx+-2] < c_b)
		            if (i[posy+3][posx+-1] < c_b)
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		     else
		      if (i[posy+-3][posx+-1] < c_b)
		       if (i[posy+-2][posx+-2] < c_b)
		        if (i[posy+-1][posx+-3] < c_b)
		         if (i[posy+0][posx+-3] < c_b)
		          if (i[posy+1][posx+-3] < c_b)
		           if (i[posy+2][posx+-2] < c_b)
		            if (i[posy+3][posx+-1] < c_b)
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		    else
		     if (i[posy+-3][posx+0] < c_b)
		      if (i[posy+-3][posx+-1] < c_b)
		       if (i[posy+-2][posx+-2] < c_b)
		        if (i[posy+-1][posx+-3] < c_b)
		         if (i[posy+0][posx+-3] < c_b)
		          if (i[posy+1][posx+-3] < c_b)
		           if (i[posy+2][posx+-2] < c_b)
		            if (i[posy+3][posx+-1] < c_b)
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		   else
		    if (i[posy+-3][posx+1] > cb) 
		     if (i[posy+0][posx+3] > cb) 
		      if (i[posy+-1][posx+3] > cb) 
		       if (i[posy+-2][posx+2] > cb) 
		        if (i[posy+-3][posx+0] > cb) 
		         if (i[posy+-3][posx+-1] > cb) 
		          if (i[posy+-2][posx+-2] > cb) 
		           if (i[posy+-1][posx+-3] > cb) 
		            if (i[posy+0][posx+-3] > cb) 
		             if (i[posy+1][posx+-3] > cb) 
		              if (i[posy+2][posx+-2] > cb) 
		               if (i[posy+3][posx+-1] > cb) 
		                return true;
		               else
		                return false;
		              else
		               return false;
		             else
		              return false;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else if (i[posy+-3][posx+1] < c_b) 
		     if (i[posy+-3][posx+0] < c_b)
		      if (i[posy+-3][posx+-1] < c_b)
		       if (i[posy+-2][posx+-2] < c_b)
		        if (i[posy+-1][posx+-3] < c_b)
		         if (i[posy+0][posx+-3] < c_b)
		          if (i[posy+1][posx+-3] < c_b)
		           if (i[posy+2][posx+-2] < c_b)
		            if (i[posy+3][posx+-1] < c_b)
		             return true;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		  else
		   if (i[posy+-2][posx+2] > cb) 
		    if (i[posy+0][posx+3] > cb) 
		     if (i[posy+-1][posx+3] > cb) 
		      if (i[posy+-3][posx+1] > cb) 
		       if (i[posy+-3][posx+0] > cb) 
		        if (i[posy+-3][posx+-1] > cb) 
		         if (i[posy+-2][posx+-2] > cb) 
		          if (i[posy+-1][posx+-3] > cb) 
		           if (i[posy+0][posx+-3] > cb) 
		            if (i[posy+1][posx+-3] > cb) 
		             if (i[posy+2][posx+-2] > cb) 
		              if (i[posy+1][posx+3] > cb) 
		               return true;
		              else
		               if (i[posy+3][posx+-1] > cb) 
		                return true;
		               else
		                return false;
		             else
		              return false;
		            else
		             return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else if (i[posy+-2][posx+2] < c_b) 
		    if (i[posy+-3][posx+1] < c_b)
		     if (i[posy+-3][posx+0] < c_b)
		      if (i[posy+-3][posx+-1] < c_b)
		       if (i[posy+-2][posx+-2] < c_b)
		        if (i[posy+-1][posx+-3] < c_b)
		         if (i[posy+0][posx+-3] < c_b)
		          if (i[posy+1][posx+-3] < c_b)
		           if (i[posy+2][posx+-2] < c_b)
		            if (i[posy+3][posx+-1] < c_b)
		             return true;
		            else
		             if (i[posy+1][posx+3] < c_b)
		              if (i[posy+0][posx+3] < c_b)
		               if (i[posy+-1][posx+3] < c_b)
		                return true;
		               else
		                return false;
		              else
		               return false;
		             else
		              return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else
		    return false;
		 else
		  if (i[posy+-1][posx+3] > cb) 
		   if (i[posy+0][posx+3] > cb) 
		    if (i[posy+-2][posx+2] > cb) 
		     if (i[posy+-3][posx+1] > cb) 
		      if (i[posy+-3][posx+0] > cb) 
		       if (i[posy+-3][posx+-1] > cb) 
		        if (i[posy+-2][posx+-2] > cb) 
		         if (i[posy+-1][posx+-3] > cb) 
		          if (i[posy+0][posx+-3] > cb) 
		           if (i[posy+1][posx+-3] > cb) 
		            if (i[posy+1][posx+3] > cb) 
		             if (i[posy+2][posx+2] > cb) 
		              return true;
		             else
		              if (i[posy+2][posx+-2] > cb) 
		               return true;
		              else
		               return false;
		            else
		             if (i[posy+2][posx+-2] > cb) 
		              if (i[posy+3][posx+-1] > cb) 
		               return true;
		              else
		               return false;
		             else
		              return false;
		           else
		            return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else
		    return false;
		  else if (i[posy+-1][posx+3] < c_b) 
		   if (i[posy+-2][posx+2] < c_b)
		    if (i[posy+-3][posx+1] < c_b)
		     if (i[posy+-3][posx+0] < c_b)
		      if (i[posy+-3][posx+-1] < c_b)
		       if (i[posy+-2][posx+-2] < c_b)
		        if (i[posy+-1][posx+-3] < c_b)
		         if (i[posy+0][posx+-3] < c_b)
		          if (i[posy+1][posx+-3] < c_b)
		           if (i[posy+2][posx+-2] < c_b)
		            if (i[posy+3][posx+-1] < c_b)
		             return true;
		            else
		             if (i[posy+1][posx+3] < c_b)
		              if (i[posy+0][posx+3] < c_b)
		               return true;
		              else
		               return false;
		             else
		              return false;
		           else
		            if (i[posy+2][posx+2] < c_b)
		             if (i[posy+1][posx+3] < c_b)
		              if (i[posy+0][posx+3] < c_b)
		               return true;
		              else
		               return false;
		             else
		              return false;
		            else
		             return false;
		          else
		           return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else
		    return false;
		  else
		   return false;
		else
		 if (i[posy+0][posx+3] > cb) 
		  if (i[posy+-1][posx+3] > cb) 
		   if (i[posy+-2][posx+2] > cb) 
		    if (i[posy+-3][posx+1] > cb) 
		     if (i[posy+-3][posx+0] > cb) 
		      if (i[posy+-3][posx+-1] > cb) 
		       if (i[posy+-2][posx+-2] > cb) 
		        if (i[posy+-1][posx+-3] > cb) 
		         if (i[posy+0][posx+-3] > cb) 
		          if (i[posy+1][posx+3] > cb) 
		           if (i[posy+2][posx+2] > cb) 
		            if (i[posy+3][posx+1] > cb) 
		             return true;
		            else
		             if (i[posy+1][posx+-3] > cb) 
		              return true;
		             else
		              return false;
		           else
		            if (i[posy+1][posx+-3] > cb) 
		             if (i[posy+2][posx+-2] > cb) 
		              return true;
		             else
		              return false;
		            else
		             return false;
		          else
		           if (i[posy+1][posx+-3] > cb) 
		            if (i[posy+2][posx+-2] > cb) 
		             if (i[posy+3][posx+-1] > cb) 
		              return true;
		             else
		              return false;
		            else
		             return false;
		           else
		            return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else
		    return false;
		  else
		   return false;
		 else if (i[posy+0][posx+3] < c_b) 
		  if (i[posy+-1][posx+3] < c_b)
		   if (i[posy+-2][posx+2] < c_b)
		    if (i[posy+-3][posx+1] < c_b)
		     if (i[posy+-3][posx+0] < c_b)
		      if (i[posy+-3][posx+-1] < c_b)
		       if (i[posy+-2][posx+-2] < c_b)
		        if (i[posy+-1][posx+-3] < c_b)
		         if (i[posy+0][posx+-3] < c_b)
		          if (i[posy+1][posx+3] < c_b)
		           if (i[posy+2][posx+2] < c_b)
		            if (i[posy+3][posx+1] < c_b)
		             return true;
		            else
		             if (i[posy+1][posx+-3] < c_b)
		              return true;
		             else
		              return false;
		           else
		            if (i[posy+1][posx+-3] < c_b)
		             if (i[posy+2][posx+-2] < c_b)
		              return true;
		             else
		              return false;
		            else
		             return false;
		          else
		           if (i[posy+1][posx+-3] < c_b)
		            if (i[posy+2][posx+-2] < c_b)
		             if (i[posy+3][posx+-1] < c_b)
		              return true;
		             else
		              return false;
		            else
		             return false;
		           else
		            return false;
		         else
		          return false;
		        else
		         return false;
		       else
		        return false;
		      else
		       return false;
		     else
		      return false;
		    else
		     return false;
		   else
		    return false;
		  else
		   return false;
		 else
		  return false;
	}
}
