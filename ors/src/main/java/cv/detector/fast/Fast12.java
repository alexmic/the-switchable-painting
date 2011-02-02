package cv.detector.fast;

import java.util.Arrays;
import java.util.Collections;

public class Fast12 {
	
	public static FeaturePoint[] detect(int[][] image, int w, int h, int threshold, int N)
	{
		// um.. guessestimate number of corners.
		FeaturePoint[] corners = new FeaturePoint[20000];
		int count = 0;
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
				corners[count] = new FeaturePoint(x, y);
				count++;
			}
		}
		for (int i = 0; i < count; ++i) {
			int x = corners[i].x();
			int y = corners[i].y();
			corners[i].score(cornerScore(image, x, y));
		}
		FeaturePoint[] ret = Arrays.copyOf(corners, count);
		Arrays.sort(ret, Collections.reverseOrder());
		return Arrays.copyOf(ret, (N < count)?N:count);
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
